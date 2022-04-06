package com.bestpractises.razisample.network

import javax.inject.Inject


class ApiManager @Inject constructor( val apiServiceSSO: ApiServiceSSO, public val apiServiceMedia: ApiServiceMedia, public val apiService : ApiService) {

}