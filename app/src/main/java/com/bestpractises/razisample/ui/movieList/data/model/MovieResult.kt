package com.bestpractises.razisample.ui.movieList.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    @SerializedName("video")
    @Expose
    val video: Boolean,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double
)