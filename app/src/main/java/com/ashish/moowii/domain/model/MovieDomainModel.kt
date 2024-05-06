package com.ashish.moowii.domain.model

data class MovieDomainModel(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Float,
    val overview: String,
)
