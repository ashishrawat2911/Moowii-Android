package com.ashish.moowii.presentation.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.usecase.SearchMoviesUseCase
import com.ashish.moowii.utils.AppDispatchers
import com.ashish.moowii.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val appDispatchers: AppDispatchers,
) : ViewModel() {
    val uiState: StateFlow<List<MovieDomainModel>>
        get() = _uiState
    private val _uiState = MutableStateFlow<List<MovieDomainModel>>(mutableListOf())
    val detailErrorState: SharedFlow<String>
        get() = _detailErrorState
    private val _detailErrorState = MutableSharedFlow<String>()

    fun searchMovies(query: String) {
        viewModelScope.launch(appDispatchers.IO) {
            searchMoviesUseCase(query).collectLatest { movieResult ->
                when (movieResult) {
                    is NetworkResult.Success -> {
                        _uiState.value = movieResult.data
                    }

                    is NetworkResult.Error -> {
                        _detailErrorState.emit(movieResult.error)
                    }
                }
            }
        }
    }
}