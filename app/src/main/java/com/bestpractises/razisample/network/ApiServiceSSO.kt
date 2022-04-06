package com.bestpractises.razisample.network

import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceSSO {

    @FormUrlEncoded
    @POST("LOGIN")
    suspend fun login(@FieldMap params: Map<String, String>): Response<Any>

}