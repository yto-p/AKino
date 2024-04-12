package com.mtuci.akino.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL = "https://api.kinopoisk.dev/v1.4/"

    private fun provideApiService(): ApiService{
        val httpClient = OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-API-KEY", TOKEN)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val apiService: ApiService = provideApiService()
}