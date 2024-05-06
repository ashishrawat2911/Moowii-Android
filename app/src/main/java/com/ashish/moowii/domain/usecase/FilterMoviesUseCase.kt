package com.ashish.moowii.domain.usecase

import com.ashish.moowii.domain.model.MovieDomainModel

interface FilterMoviesUseCase {
    operator fun invoke(movies: List<MovieDomainModel>, text: String): List<MovieDomainModel>
}
