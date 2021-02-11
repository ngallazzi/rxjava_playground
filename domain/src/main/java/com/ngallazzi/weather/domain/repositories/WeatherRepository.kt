package com.ngallazzi.weather.domain.repositories

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getCurrentWeather(city: String, forceUpdate: Boolean = false): Single<CurrentWeather>

    fun getWeekWeather(
        latitude: Double,
        longitude: Double,
        forceUpdate: Boolean = false
    ): Single<WeekWeather>
}