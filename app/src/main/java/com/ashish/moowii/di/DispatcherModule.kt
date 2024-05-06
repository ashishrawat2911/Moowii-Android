package com.ashish.moowii.di

import com.ashish.moowii.utils.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {
    @Provides
    fun providesAppDispatchers(): AppDispatchers {
        return AppDispatchers()
    }
}
