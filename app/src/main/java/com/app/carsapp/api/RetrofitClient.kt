package com.app.carsapp.api

import com.app.carsapp.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
        private val retrofitBuilder : Retrofit.Builder = Retrofit.Builder()
              .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    private val retrofit : Retrofit = retrofitBuilder.build()

    private val httpClient = OkHttpClient.Builder()

    fun <T> createService(serviceClass : Class<T>) : T{
        return retrofit.create(serviceClass)
    }
}