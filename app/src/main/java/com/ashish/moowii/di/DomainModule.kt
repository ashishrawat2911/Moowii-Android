package com.ashish.moowii.di

import com.ashish.moowii.domain.usecase.FilterMoviesUseCase
import com.ashish.moowii.domain.usecase.GetMovieDetailsUseCase
import com.ashish.moowii.domain.usecase.GetMoviesUseCase
import com.ashish.moowii.domain.usecase.SearchMoviesUseCase
import com.ashish.moowii.domain.usecase.impl.FilterMoviesUseCaseImpl
import com.ashish.moowii.domain.usecase.impl.GetMovieDetailsUseCaseImpl
import com.ashish.moowii.domain.usecase.impl.GetMoviesUseCaseImpl
import com.ashish.moowii.domain.usecase.impl.SearchMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {
    @Binds
    abstract fun bindsGetMoviesUseCase(getMoviesUseCaseImpl: GetMoviesUseCaseImpl): GetMoviesUseCase

    @Binds
    abstract fun bindsGetMoviesDetailsUseCase(getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl): GetMovieDetailsUseCase

    @Binds
    abstract fun bindsFilterMoviesUseCase(filterMoviesUseCaseImpl: FilterMoviesUseCaseImpl): FilterMoviesUseCase

    @Binds
    abstract fun bindsSearchMoviesUseCaseImpl(searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl): SearchMoviesUseCase
}
