package com.ashish.moowii.presentation.features.moviedetails.state


sealed class MovieDetailState {
    object Loading : MovieDetailState()
    data class Success(val movie: MovieDetailStateData) : MovieDetailState()
    data class Error(val error: String) : MovieDetailState()
}
