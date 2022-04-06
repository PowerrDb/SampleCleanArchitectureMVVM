package com.bestpractises.razisample.ui.movieList.domain

import retrofit2.Response


interface MovieListRepository {
    suspend fun getMovies(page:Int): Response<ArrayList<Any>>
}