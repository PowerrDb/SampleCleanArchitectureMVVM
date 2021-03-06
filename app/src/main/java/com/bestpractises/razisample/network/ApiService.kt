package com.bestpractises.razisample.network

import com.bestpractises.razisample.ui.movieList.data.model.MovieItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList


interface ApiService {


    @GET("/3/movie/popular")
    suspend fun getMovieList(@Query("page") page : Int): Response<MovieItem>

}
