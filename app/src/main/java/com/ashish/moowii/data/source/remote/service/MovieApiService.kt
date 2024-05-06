package com.ashish.moowii.data.source.remote.service

import com.ashish.moowii.data.source.remote.service.model.MovieListResponseModel
import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun popularMovies(@Query("page") page: Int): MovieListResponseModel

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): MovieListResponseModel

    @GET("movie/{movie_id}")
    suspend fun movieDetails(@Path("movie_id") movieId: Int): MovieResponseModel
}