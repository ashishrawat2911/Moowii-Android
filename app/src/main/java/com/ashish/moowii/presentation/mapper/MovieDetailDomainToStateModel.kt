package com.ashish.moowii.presentation.mapper

import com.ashish.moowii.domain.model.MovieDetailDomainModel
import com.ashish.moowii.presentation.features.moviedetails.state.MovieDetailStateData
import com.ashish.moowii.utils.Constants.Companion.movieImagePath
import com.ashish.moowii.utils.Mapper
import javax.inject.Inject

class MovieDetailDomainToStateModel @Inject constructor() :
    Mapper<MovieDetailDomainModel, MovieDetailStateData> {
    override fun map(type: MovieDetailDomainModel): MovieDetailStateData {
        with(type) {
            return MovieDetailStateData(
                detailTitle = detailTitle,
                detailVoteAverage = detailVoteAverage,
                detailOverview = detailOverview,
                detailBackdropPath = movieImagePath + detailBackdropPath,
            )
        }
    }
}