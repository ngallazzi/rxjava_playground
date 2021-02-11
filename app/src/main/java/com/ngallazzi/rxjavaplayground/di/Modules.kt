package com.ngallazzi.rxjavaplayground.di

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.mappers.ForecastsMapper
import com.ngallazzi.rxjavaplayground.ui.ForecastViewModel
import com.ngallazzi.weather.data.WeatherRepositoryImpl
import com.ngallazzi.weather.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.weather.data.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.domain.repositories.WeatherRepository
import com.ngallazzi.weather.domain.usecases.GetDailyForecastsUseCase
import com.ngallazzi.weather.domain.usecases.GetWeatherUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    factory { NetworkModule().createWeatherApiRx(BuildConfig.API_URL) }
}

val viewModelsModule = module {
    factory { WeatherLocalDataSource() }
    factory { WeatherRemoteDataSourceRx(get(), BuildConfig.API_KEY) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            get(), get()
        )
    }
    factory { GetWeatherUseCase(get()) }
    factory { GetDailyForecastsUseCase(get()) }
    factory { Utils() }
    factory { ForecastsMapper(get()) }

    viewModel {
        ForecastViewModel(get(), get(), get())
    }
}