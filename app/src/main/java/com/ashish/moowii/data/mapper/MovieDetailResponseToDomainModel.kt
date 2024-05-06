package com.ashish.moowii.data.mapper

import com.ashish.moowii.data.source.remote.service.model.MovieResponseModel
import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.utils.Constants
import com.ashish.moowii.utils.Mapper
import javax.inject.Inject

class MovieDetailResponseToDomainModel @Inject constructor() :
    Mapper<MovieResponseModel, MovieDetailDomainModel> {
    override fun map(type: MovieResponseModel): MovieDetailDomainModel {
        with(type) {
            return MovieDetailDomainModel(
                detailTitle = title,
                detailBackdropPath = backdropPath ?: Constants.noImage,
                detailOverview = overview,
                detailVoteAverage = voteAverage,
            )
        }
    }
}
