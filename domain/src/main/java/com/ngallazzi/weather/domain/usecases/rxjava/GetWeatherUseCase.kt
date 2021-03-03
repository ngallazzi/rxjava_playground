package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String) = repository.getCurrentWeather(cityName)
}