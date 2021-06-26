package com.example.whattowatch.ui.films

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("display_title") val title: String,
    @SerializedName("summary_short") val description: String,
    @SerializedName("multimedia") val multimedia: Multimedia
)