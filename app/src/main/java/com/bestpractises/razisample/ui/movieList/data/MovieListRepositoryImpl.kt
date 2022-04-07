package com.bestpractises.razisample.ui.movieList.data

import com.bestpractises.razisample.network.ApiService
import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import com.bestpractises.razisample.ui.movieList.domain.MovieListRepository
import retrofit2.Response
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(val apiService: ApiService):MovieListRepository {
    override suspend fun getMovies(page: Int): Response<MovieItem> {
        return   apiService.getMovieList(page)
    }

}

