package com.ngallazzi.rxjavaplayground

import android.app.Application
import com.ngallazzi.rxjavaplayground.di.ServiceLocator
import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository
import com.ngallazzi.rxjavaplayground.domain.usecases.GetWeatherUseCase

class WeatherApplication : Application() {
    private val weatherRepository: WeatherRepository
        get() = ServiceLocator.provideWeatherRepository(this)

    val getWeatherUseCase: GetWeatherUseCase
        get() = GetWeatherUseCase(weatherRepository)
}