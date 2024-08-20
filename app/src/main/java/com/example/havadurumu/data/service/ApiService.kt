package com.example.havadurumu.data.service

import com.example.havadurumu.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: apikey ")
    @GET("weather/getWeather")
    suspend fun getWeather(@Query("data.lang") lang: String, @Query("data.city") city: String): Response<WeatherModel?>
}