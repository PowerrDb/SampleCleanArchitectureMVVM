package com.bestpractises.razisample.di

import com.bestpractises.razisample.network.ApiManager
import com.bestpractises.razisample.network.ApiService
import com.bestpractises.razisample.network.ApiServiceMedia
import com.bestpractises.razisample.network.ApiServiceSSO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {


    @Provides
    fun provideApiManager(
        apiServiceSSO: ApiServiceSSO,
        apiServiceMedia: ApiServiceMedia,
        apiService: ApiService
    ): ApiManager {
        return ApiManager(
            apiServiceSSO,
            apiServiceMedia,
            apiService
        )

    }


}