package com.ngallazzi.weather.domain.repositories.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getCurrentWeather(city: String): Single<CurrentWeather>

    fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates
    ): Single<WeekWeather>

    fun saveCityWeather(weather: CurrentWeather)

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weekWeather: WeekWeather)
}