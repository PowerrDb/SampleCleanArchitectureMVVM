package com.bestpractises.razisample.di


import com.bestpractises.razisample.ui.movieList.data.MovieListRepositoryImpl
import com.bestpractises.razisample.ui.movieList.domain.MovieListUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
  @Provides
  fun provideMovieListUseCase(repositoryImpl: MovieListRepositoryImpl): MovieListUsecase {
      return MovieListUsecase(repositoryImpl)
  }

}