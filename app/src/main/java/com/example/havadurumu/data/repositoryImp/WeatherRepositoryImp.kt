package com.example.havadurumu.data.repositoryImp

import com.example.havadurumu.data.model.WeatherModel
import com.example.havadurumu.data.repository.WeatherRepository
import com.example.havadurumu.data.service.ApiService
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(private val service: ApiService) : WeatherRepository {
    override suspend fun getWeather(land: String, city: String): Response<WeatherModel?> {
        return try {
            val result = service.getWeather(land, city)
            if (result.code() == 200) {
                result
            } else {
                val errorBody = result.errorBody()?.string()
                Response.error(result.code(), errorBody.toString().toResponseBody(null))
            }
        } catch (e: Exception) {
            val errorBody = e.message
            Response.error(500, errorBody.toString().toResponseBody(null))
        }
    }

}