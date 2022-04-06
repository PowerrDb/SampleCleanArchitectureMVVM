package com.bestpractises.razisample.network


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("detail")
    @Expose
    val detail: String,
    @SerializedName("status")
    @Expose
    val status: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("traceId")
    @Expose
    val traceId: String,
    @SerializedName("type")
    @Expose
    val type: String
)