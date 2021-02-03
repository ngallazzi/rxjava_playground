package com.ngallazzi.rxjavaplayground.domain.usecases

import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository

class GetDailyForecastsUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double, apiKey: String) =
        repository.getDailyForecasts(latitude, longitude, apiKey)
}