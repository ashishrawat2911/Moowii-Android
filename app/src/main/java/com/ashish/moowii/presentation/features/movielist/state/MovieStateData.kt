package com.ashish.moowii.presentation.features.movielist.state

data class MovieStateData(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Float,
    val overview: String,
)
