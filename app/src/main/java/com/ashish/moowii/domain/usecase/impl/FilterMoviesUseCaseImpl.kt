package com.ashish.moowii.domain.usecase.impl

import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.domain.usecase.FilterMoviesUseCase
import javax.inject.Inject

class FilterMoviesUseCaseImpl @Inject constructor() : FilterMoviesUseCase {
    override operator fun invoke(
        movies: List<MovieDomainModel>,
        text: String
    ) = movies.filter {
        it.title.lowercase().contains(text.lowercase())
    }
}
