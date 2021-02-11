package com.ngallazzi.weather.data.api

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Single

class WeatherRemoteDataSourceRx(
    private val service: WeatherApiRx,
    private val apiKey: String
) {
    fun getCityWeather(cityName: String): Single<CurrentWeather> {
        return service.getCityWeather(cityName, apiKey)
    }

    fun getWeekWeather(
        latitude: Double,
        longitude: Double
    ): Single<WeekWeather> {
        return service.getWeekWeatherByCoordinates(latitude, longitude, appid = apiKey)
    }
}