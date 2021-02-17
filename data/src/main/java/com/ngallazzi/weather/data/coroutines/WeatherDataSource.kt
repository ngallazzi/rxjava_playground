package com.ngallazzi.weather.data.coroutines

import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface WeatherDataSource {
    suspend fun getCityWeather(cityName: String): Result<CurrentWeather>

    fun saveCityWeather(cityName: String, currentWeather: CurrentWeather)

    suspend fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Result<WeekWeather>

    fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather)
}