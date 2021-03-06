package com.ngallazzi.weather.domain.repositories.coroutines

import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    suspend fun getCurrentWeather(
        city: String,
        forceUpdate: Boolean = false
    ): Result<CurrentWeather>

    suspend fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        forceUpdate: Boolean = false
    ): Result<WeekWeather>
}