package com.ngallazzi.weather.domain.usecases.rxjava

import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository
import io.reactivex.rxjava3.core.Maybe

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String, forceUpdate: Boolean): Maybe<CurrentWeather> {
        return repository.getCurrentWeather(cityName, forceUpdate)
    }

}