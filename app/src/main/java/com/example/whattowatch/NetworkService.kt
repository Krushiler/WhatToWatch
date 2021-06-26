package com.example.whattowatch

import com.example.whattowatch.retrofit.TokenInterceptor
import com.example.whattowatch.ui.films.FilmsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private val BASE_URL = "https://api.nytimes.com"

    private val client:OkHttpClient = OkHttpClient.Builder().addNetworkInterceptor(TokenInterceptor()).build()

    val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getFilmsApi():FilmsApi{
        return retrofit.create(FilmsApi::class.java)
    }
}