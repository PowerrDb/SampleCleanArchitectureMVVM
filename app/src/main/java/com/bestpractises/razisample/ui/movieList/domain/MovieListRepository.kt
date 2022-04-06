package com.bestpractises.razisample.ui.movieList.domain

import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import retrofit2.Response


interface MovieListRepository {
    suspend fun getMovies(page:Int): Response<MovieItem>
}