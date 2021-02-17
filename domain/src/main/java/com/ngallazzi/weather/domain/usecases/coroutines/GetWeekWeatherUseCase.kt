package com.ngallazzi.weather.domain.usecases.coroutines

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.coroutines.WeatherRepository

class GetWeekWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(coordinates: CurrentWeather.Coordinates, forceUpdate: Boolean) =
        repository.getWeekWeather(coordinates, forceUpdate)
}