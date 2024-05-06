package com.ashish.moowii.presentation.navigation



sealed class Screen(val route: String) {
    data object MovieList : Screen("MovieList")
    data object MovieSearch : Screen("MovieSearch")
    data object MovieDetails : Screen("movie_details_screen/{movieId}") {
        fun withMovieId(movieId: Int) = "movie_details_screen/$movieId"
    }
}