package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.data.mappers.ApiResponseMapper
import com.ngallazzi.rxjavaplayground.domain.entities.City
import com.ngallazzi.rxjavaplayground.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSourceCoroutines(
    private val service: WeatherApiCoroutines,
    private val mapper: ApiResponseMapper
) {
    suspend fun getCityWeather(cityName: String, apiKey: String): Result<City> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getCityWeather(cityName, apiKey)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.fromWeatherApiResponseToCityForecast(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}