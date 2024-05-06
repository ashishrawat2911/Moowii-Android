package com.ashish.moowii.domain.usecase

import androidx.paging.PagingData
import com.ashish.moowii.utils.NetworkResult
import com.ashish.moowii.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke(): Flow<PagingData<MovieDomainModel>>
}
