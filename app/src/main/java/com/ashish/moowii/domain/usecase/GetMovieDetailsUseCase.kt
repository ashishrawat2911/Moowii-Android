package com.ashish.moowii.domain.usecase

import com.ashish.moowii.utils.NetworkResult
import com.ashish.moowii.domain.model.MovieDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(movieId: Int): Flow<NetworkResult<MovieDetailDomainModel>>
}
