package com.bestpractises.razisample.ui.movieList.data

import com.bestpractises.razisample.network.ApiManager
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.domain.MovieListRepository
import retrofit2.Response
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(val apiManager: ApiManager):MovieListRepository {
    override suspend fun getMovies(page: Int): Response<MovieItem> {
        return  apiManager.apiService.getMovieList(page)
    }

}

