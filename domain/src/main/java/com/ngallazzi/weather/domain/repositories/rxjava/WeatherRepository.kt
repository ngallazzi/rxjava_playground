package com.ngallazzi.weather.domain.repositories.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getCurrentWeather(city: String, forceUpdate: Boolean = false): Maybe<CurrentWeather>

    fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        forceUpdate: Boolean = false
    ): Maybe<WeekWeather>

    fun saveCityWeather(city: String, weather: CurrentWeather)

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weekWeather: WeekWeather)
}