package com.ashish.moowii.presentation.mapper

import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.utils.Constants.Companion.movieImagePath
import com.ashish.moowii.utils.Mapper
import com.ashish.moowii.presentation.features.movielist.state.MovieStateData
import javax.inject.Inject

class MovieDomainToStateModel @Inject constructor() : Mapper<MovieDomainModel, MovieStateData> {
    override fun map(type: MovieDomainModel): MovieStateData {
        with(type) {
            return MovieStateData(
                id = id,
                posterPath = movieImagePath + posterPath,
                title = title,
                voteAverage = voteAverage,
                overview = overview
            )
        }
    }
}
