package com.ashish.moowii.domain.usecase.impl

import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.domain.repository.MovieRepository
import com.ashish.moowii.domain.usecase.GetMovieDetailsUseCase
import com.ashish.moowii.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovieDetailsUseCase {
    override suspend operator fun invoke(movieId: Int): Flow<NetworkResult<MovieDetailDomainModel>> {
        Timber.e(Thread.currentThread().name)
        return repository.getMovieDetails(movieId)
    }
}
