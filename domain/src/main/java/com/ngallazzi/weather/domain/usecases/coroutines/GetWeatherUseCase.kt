package com.ngallazzi.weather.domain.usecases.coroutines

import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.coroutines.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator suspend fun invoke(cityName: String, forceUpdate: Boolean): Result<CurrentWeather> {
        return repository.getCurrentWeather(cityName, forceUpdate)
    }
}