package com.ashish.moowii.domain.model

import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel
import com.google.gson.annotations.SerializedName

data class MovieListDomainModel(
    val page: Int,

    val totalResults: Int,

    val totalPages: Int,

    val results: List<MovieDomainModel>,
)
