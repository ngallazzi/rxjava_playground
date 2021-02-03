package com.ngallazzi.rxjavaplayground.domain.usecases

import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String, apiKey: String) =
        repository.getCityWeather(cityName, apiKey)
}