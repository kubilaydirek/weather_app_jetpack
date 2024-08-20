package com.example.havadurumu.data.repository

import com.example.havadurumu.data.model.WeatherModel
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeather(land: String, city: String): Response<WeatherModel?>
}