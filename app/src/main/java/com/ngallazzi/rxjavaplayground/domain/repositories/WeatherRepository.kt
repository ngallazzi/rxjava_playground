package com.ngallazzi.rxjavaplayground.domain.repositories

import com.ngallazzi.rxjavaplayground.data.entities.CityForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.domain.entities.City
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getCityWeather(city: String, apiKey: String): Single<CityForecast>

    fun getDailyForecasts(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Single<List<DailyForecast>>
}