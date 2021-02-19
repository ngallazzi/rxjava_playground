package com.ngallazzi.weather.data.coroutines.remote

import com.ngallazzi.weather.data.coroutines.WeatherDataSource
import com.ngallazzi.weather.data.coroutines.api.WeatherApi
import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather

class WeatherRemoteDataSource(
    private val service: WeatherApi,
    private val apiKey: String
) : WeatherDataSource {

    override suspend fun getCityWeather(cityName: String): Result<CurrentWeather> {
        return try {
            val response = service.getCityWeather(cityName, apiKey)
            Result.Success(response.body()!!)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun saveCityWeather(currentWeather: CurrentWeather) {
        //no op
        TODO("Not yet implemented")
    }

    override suspend fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Result<WeekWeather> {
        return try {
            val response =
                service.getWeekWeatherByCoordinates(
                    coordinates.lat,
                    coordinates.lon,
                    appid = apiKey
                )
            Result.Success(response.body()!!)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) {
        //no op
        TODO("Not yet implemented")
    }
}