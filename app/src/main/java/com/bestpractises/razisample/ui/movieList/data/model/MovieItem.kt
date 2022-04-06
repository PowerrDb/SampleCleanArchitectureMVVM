package com.bestpractises.razisample.ui.movieList.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("results")
    @Expose
    val results: List<MovieResult>,
    @SerializedName("total_results")
    @Expose
    val totalResults: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int
)