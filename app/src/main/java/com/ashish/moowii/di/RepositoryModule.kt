package com.ashish.moowii.di

import com.ashish.moowii.data.repository.MovieRepositoryImpl
import com.ashish.moowii.data.source.MovieRemoteDataSource
import com.ashish.moowii.data.source.remote.MovieRemoteDataSourceImpl
import com.ashish.moowii.data.store.MovieDataStore
import com.ashish.moowii.data.store.impl.MovieDataStoreImpl
import com.ashish.moowii.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
    @Binds
    abstract fun bindsMovieDataStore(movieDataStoreImpl: MovieDataStoreImpl): MovieDataStore
    @Binds
    abstract fun bindsMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}
