package com.works.configs

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val base_url = "https://dummyjson.com/"
    private var retrofit: Retrofit? = null

    private val clientConfig = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(clientConfig)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

}