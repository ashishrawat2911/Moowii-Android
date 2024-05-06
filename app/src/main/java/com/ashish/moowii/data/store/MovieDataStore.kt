package com.ashish.moowii.data.store

import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.model.MovieListDomainModel

interface MovieDataStore {
    suspend fun getMovies(page: Int): MovieListDomainModel

    suspend fun searchMovies(query: String): List<MovieDomainModel>

    suspend fun getMovieDetails(movieId: Int): MovieDetailDomainModel
}

