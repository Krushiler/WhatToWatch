package com.example.whattowatch.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = "qpTw7iwZcIDJj0JZZhyr6YWHCfHbak7c"
        val url = original.url().newBuilder().addQueryParameter("apikey", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}