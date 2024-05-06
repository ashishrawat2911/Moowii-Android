package com.ashish.moowii.data.store.impl

import com.ashish.moowii.data.mapper.MovieDetailResponseToDomainModel
import com.ashish.moowii.data.mapper.MovieResponseToDomainModel
import com.ashish.moowii.data.source.MovieRemoteDataSource
import com.ashish.moowii.data.store.MovieDataStore
import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.model.MovieListDomainModel
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieResponseToDomainModel: MovieResponseToDomainModel,
    private val movieDetailResponseToDomainModel: MovieDetailResponseToDomainModel,
) : MovieDataStore {
    override suspend fun getMovies(page: Int): MovieListDomainModel {
        val movieList = movieRemoteDataSource.getMovies(page);
        return MovieListDomainModel(
            page = movieList.page,
            results = movieList.results.map { movieResponse ->
                movieResponseToDomainModel.map(movieResponse)
            },
            totalPages = movieList.totalPages,
            totalResults = movieList.totalResults,
        )
    }

    override suspend fun searchMovies(query: String): List<MovieDomainModel> {
        val movieList = movieRemoteDataSource.searchMovies(query);
      return  movieList.results.map { movieResponse ->
            movieResponseToDomainModel.map(movieResponse)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailDomainModel {
        return movieDetailResponseToDomainModel.map(
            movieRemoteDataSource.getMovieDetails(
                movieId
            )
        )
    }
}
