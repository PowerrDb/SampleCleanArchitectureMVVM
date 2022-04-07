/*
package com.bestpractises.razisample.di

import com.bestpractises.razisample.network.ApiManager
import com.bestpractises.razisample.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {


    @Provides
    fun provideApiManager(
        apiService: ApiService
    ): ApiManager {
        return ApiManager(
            apiService
        )

    }


}*/
