package com.example.whattowatch.ui.films

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {
    @GET("/svc/movies/v2/reviews/all.json")
    fun getFilms(@Query("offset") offset:Int) : Call<Response>
}