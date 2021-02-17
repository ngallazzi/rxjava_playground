package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository

class SaveWeekWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) =
        repository.saveWeekWeather(coordinates, weather)
}