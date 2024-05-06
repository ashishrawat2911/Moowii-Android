package com.ashish.moowii.domain.usecase

import androidx.paging.PagingData
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {
    suspend operator fun invoke(query:String): Flow<NetworkResult<List<MovieDomainModel>>>
}
