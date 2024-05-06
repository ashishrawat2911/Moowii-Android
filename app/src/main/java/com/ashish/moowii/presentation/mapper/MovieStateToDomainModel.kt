package com.ashish.moowii.presentation.mapper

import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.utils.Mapper
import com.ashish.moowii.presentation.features.movielist.state.MovieStateData
import javax.inject.Inject

class MovieStateToDomainModel @Inject constructor() : Mapper<MovieStateData, MovieDomainModel> {
    override fun map(type: MovieStateData): MovieDomainModel {
        with(type) {
            return MovieDomainModel(
                id = id,
                posterPath = posterPath,
                title = title,
                voteAverage = voteAverage,
                overview = overview
            )
        }
    }
}
