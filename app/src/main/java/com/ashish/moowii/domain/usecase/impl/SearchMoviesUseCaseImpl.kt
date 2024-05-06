package com.ashish.moowii.domain.usecase.impl

import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.repository.MovieRepository
import com.ashish.moowii.domain.usecase.SearchMoviesUseCase
import com.ashish.moowii.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : SearchMoviesUseCase {
    override suspend fun invoke(query: String): Flow<NetworkResult<List<MovieDomainModel>>> {
        return repository.searchMovies(query)
    }
}
