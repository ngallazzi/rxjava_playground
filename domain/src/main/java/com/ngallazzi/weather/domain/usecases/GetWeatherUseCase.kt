package com.ngallazzi.weather.domain.usecases

import com.ngallazzi.weather.domain.repositories.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) =
        repository.getCurrentWeather(cityName, true)
}