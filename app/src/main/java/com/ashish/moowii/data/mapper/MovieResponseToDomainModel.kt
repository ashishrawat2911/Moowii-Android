package com.ashish.moowii.data.mapper

import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.utils.Constants
import com.ashish.moowii.utils.Mapper
import javax.inject.Inject

class MovieResponseToDomainModel @Inject constructor() :
    Mapper<MovieResponseModel, MovieDomainModel> {
    override fun map(type: MovieResponseModel): MovieDomainModel {
        return with(type) {
            MovieDomainModel(
                id = id,
                title = title,
                posterPath = posterPath ?: Constants.noImage,
                overview = overview,
                voteAverage = voteAverage,
            )
        }
    }
}

