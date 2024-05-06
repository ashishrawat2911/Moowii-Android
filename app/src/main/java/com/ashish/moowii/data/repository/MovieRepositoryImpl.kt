package com.ashish.moowii.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashish.moowii.data.source.remote.MoviePagingSource
import com.ashish.moowii.data.store.MovieDataStore
import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.repository.MovieRepository
import com.ashish.moowii.utils.Constants
import com.ashish.moowii.utils.NetworkResult
import com.ashish.moowii.utils.getMovieErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataStore: MovieDataStore,
) : MovieRepository {

    override suspend fun getMovies(): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieDataStore)
            }
        ).flow
    }

    override suspend fun searchMovies(query: String): Flow<NetworkResult<List<MovieDomainModel>>> {
        return flow {
            try {
                emit(NetworkResult.Success(movieDataStore.searchMovies(query)))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.getMovieErrorMessage()))
            }
        };
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<NetworkResult<MovieDetailDomainModel>> {
        return flow {
            try {
                emit(NetworkResult.Success(movieDataStore.getMovieDetails(movieId)))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.getMovieErrorMessage()))
            }
        }
    }
}
