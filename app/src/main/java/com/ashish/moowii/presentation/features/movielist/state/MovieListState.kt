package com.ashish.moowii.presentation.features.movielist.state


sealed class MovieListState {
    object Loading : MovieListState()
    data class Success(val movies: List<MovieStateData>) : MovieListState()
    data class Error(val error: String) : MovieListState()
}
