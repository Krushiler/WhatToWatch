package com.example.whattowatch.ui.films

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status") val status: String,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("num_results") val resultsCount: Int,
    @SerializedName("results") val films: List<Film>
)
