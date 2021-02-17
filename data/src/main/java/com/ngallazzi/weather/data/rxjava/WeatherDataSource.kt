package com.ngallazzi.weather.data.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface WeatherDataSource {
    fun getCityWeather(cityName: String): Maybe<CurrentWeather>

    fun saveCityWeather(cityName: String, currentWeather: CurrentWeather)

    fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Maybe<WeekWeather>

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather)
}