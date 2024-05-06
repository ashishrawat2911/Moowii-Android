package com.ashish.moowii.data.source.remote

import com.ashish.moowii.data.source.remote.service.model.MovieListResponseModel
import com.ashish.moowii.data.source.MovieRemoteDataSource
import com.ashish.moowii.data.source.remote.service.MovieApiService
import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieApiService,
) : MovieRemoteDataSource {
    override suspend fun getMovies(page: Int): MovieListResponseModel {
        return service.popularMovies(page)
    }

    override suspend fun searchMovies(query: String): MovieListResponseModel {
        return service.searchMovies(query)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieResponseModel {
        return service.movieDetails(movieId)
    }
}