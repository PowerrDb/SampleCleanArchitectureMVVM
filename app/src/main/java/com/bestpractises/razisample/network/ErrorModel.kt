package com.bestpractises.razisample.network


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("status_message")
    @Expose
    val status_message: String,
    @SerializedName("status_code")
    @Expose
    val status_code: Int

)