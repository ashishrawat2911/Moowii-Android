package com.ashish.moowii.presentation.features.movielist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.usecase.GetMoviesUseCase
import com.ashish.moowii.utils.AppDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val appDispatchers: AppDispatchers,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<MovieDomainModel>> =
        MutableStateFlow(value = PagingData.empty())
    val uiState: MutableStateFlow<PagingData<MovieDomainModel>> get() = _uiState

    fun fetchMoviesList() {
        viewModelScope.launch(appDispatchers.IO) {
            getMoviesUseCase().cachedIn(viewModelScope)
                .collectLatest { movieResult ->
                    _uiState.value = movieResult
                }
        }
    }
}