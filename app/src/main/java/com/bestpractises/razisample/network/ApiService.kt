package com.bestpractises.razisample.network

import retrofit2.Response
import retrofit2.http.GET
import java.util.ArrayList


interface ApiService {


    @GET("GET_PERSON_LIST")
    suspend fun getHerds(page : Int): Response<ArrayList<Any>>

}
