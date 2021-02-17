package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository

class GetWeekWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(coordinates: CurrentWeather.Coordinates, forceUpdate: Boolean) =
        repository.getWeekWeather(coordinates, forceUpdate)
}