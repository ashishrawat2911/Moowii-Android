package com.ashish.moowii.domain.usecase.impl

import androidx.paging.PagingData
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.repository.MovieRepository
import com.ashish.moowii.domain.usecase.GetMoviesUseCase
import com.ashish.moowii.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMoviesUseCase {
    override suspend fun invoke(): Flow<PagingData<MovieDomainModel>> {
        return repository.getMovies()
    }
}
