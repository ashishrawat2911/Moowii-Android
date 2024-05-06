package com.ashish.moowii.domain.repository

import androidx.paging.PagingData
import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(): Flow<PagingData<MovieDomainModel>>

    suspend fun getMovieDetails(movieId: Int): Flow<NetworkResult<MovieDetailDomainModel>>

    suspend fun searchMovies(query: String): Flow<NetworkResult<List<MovieDomainModel>>>
}
