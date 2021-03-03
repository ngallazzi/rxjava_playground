package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository
import java.time.LocalDate

class GetDayWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(coordinates: CurrentWeather.Coordinates, currentDate: LocalDate) =
        repository.getDayWeather(coordinates, currentDate)
}