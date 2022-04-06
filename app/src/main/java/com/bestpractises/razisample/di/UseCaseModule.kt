package com.bestpractises.razisample.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
  Provides
  fun provideHerdsListUseCase(repositoryImpl: HerdsListRepositoryImpl): HerdsListUsecase {
      return HerdsListUsecase(repositoryImpl)
  }

}*/