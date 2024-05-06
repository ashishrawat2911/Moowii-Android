package com.ashish.moowii.data.source

import com.ashish.moowii.data.source.remote.service.model.MovieListResponseModel
import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel

interface MovieRemoteDataSource {
    suspend fun getMovies(page: Int): MovieListResponseModel
    suspend fun searchMovies(query: String): MovieListResponseModel
    suspend fun getMovieDetails(movieId: Int): MovieResponseModel
}

