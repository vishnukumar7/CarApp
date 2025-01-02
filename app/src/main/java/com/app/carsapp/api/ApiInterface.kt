package com.app.carsapp.api

import com.app.carsapp.model.CarResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("cars")
    suspend fun getCarsList() : Response<List<CarResponse>>

    @GET("cars/{id}")
    suspend fun getCarId(@Path("id") id: String) : Call<CarResponse>

    @GET("cars")
    suspend fun getSearchCars(@Query("search") search : String): Call<List<CarResponse>>

    @GET("cars")
    suspend fun getSortCars(@Query("sort") sort : String,@Query("order") order: String = "asc")
}