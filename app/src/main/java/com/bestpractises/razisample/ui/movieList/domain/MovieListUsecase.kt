package com.bestpractises.razisample.ui.movieList.domain

import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import retrofit2.Response
import javax.inject.Inject

class MovieListUsecase @Inject constructor(val repository: MovieListRepository) {
    suspend fun getMovies(page : Int): Response<MovieItem>  {
        return repository.getMovies(page)
    }



}