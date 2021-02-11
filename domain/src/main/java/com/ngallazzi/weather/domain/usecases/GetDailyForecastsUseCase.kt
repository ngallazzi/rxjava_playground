package com.ngallazzi.weather.domain.usecases

import com.ngallazzi.weather.domain.repositories.WeatherRepository

class GetDailyForecastsUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double) =
        repository.getWeekWeather(latitude, longitude, true)
}