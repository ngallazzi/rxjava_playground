package com.ngallazzi.rxjavaplayground

import android.app.Application
import com.ngallazzi.rxjavaplayground.di.appModule
import com.ngallazzi.rxjavaplayground.di.viewModelsModule
import com.ngallazzi.weather.data.rxjava.api.WeatherApi
import com.ngallazzi.weather.data.rxjava.remote.WeatherRemoteDataSource
import com.ngallazzi.weather.data.rxjava.repositories.WeatherRepositoryImpl
import com.ngallazzi.weather.domain.usecases.rxjava.GetDayWeatherUseCase
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    val getDayWeatherUseCase: GetDayWeatherUseCase by inject()

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(appModule, viewModelsModule)
        }
    }
}