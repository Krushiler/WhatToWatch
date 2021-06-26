package com.example.whattowatch.ui.films

import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("type") val type: String,
    @SerializedName("src") val imageSource: String,
    @SerializedName("height") val imageHeight: Int,
    @SerializedName("width") val imageWidth: Int
)
