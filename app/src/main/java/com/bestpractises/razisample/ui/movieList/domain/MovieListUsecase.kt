package com.bestpractises.razisample.ui.movieList.domain

import retrofit2.Response
import javax.inject.Inject

class MovieListUsecase @Inject constructor(val repository: MovieListRepository) {
    suspend fun getMovies(page : Int): Response<ArrayList<Any>>  {
        return repository.getMovies(page)
    }



}