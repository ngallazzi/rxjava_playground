package com.ngallazzi.rxjavaplayground.di

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.mappers.ForecastsMapper
import com.ngallazzi.rxjavaplayground.ui.rxjava.ForecastViewModel
import com.ngallazzi.weather.data.rxjava.repositories.WeatherRepositoryImpl
import com.ngallazzi.weather.data.rxjava.remote.WeatherRemoteDataSource
import com.ngallazzi.weather.data.rxjava.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository
import com.ngallazzi.weather.domain.usecases.rxjava.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory { NetworkModule().createWeatherApiRx(BuildConfig.API_URL) }
}

val viewModelsModule = module {
    factory { WeatherLocalDataSource() }
    factory { WeatherRemoteDataSource(get(), BuildConfig.API_KEY) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            get(), get()
        )
    }
    factory { GetWeatherUseCase(get()) }
    factory { GetWeekWeatherUseCase(get()) }
    factory { SaveWeatherUseCase(get()) }
    factory { SaveWeekWeatherUseCase(get()) }
    factory { GetDayWeatherUseCase(get()) }
    factory { Utils() }
    factory { ForecastsMapper(get()) }

    viewModel {
        ForecastViewModel(get(), get(), get(), get(), get())
    }
}