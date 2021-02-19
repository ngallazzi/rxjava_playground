package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository

class SaveWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(weather: CurrentWeather) =
        repository.saveCityWeather(weather)
}