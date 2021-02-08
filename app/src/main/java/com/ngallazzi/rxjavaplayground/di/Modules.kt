package com.ngallazzi.rxjavaplayground.di

import com.ngallazzi.rxjavaplayground.data.WeatherRepositoryImpl
import com.ngallazzi.rxjavaplayground.data.api.NetworkModule
import com.ngallazzi.rxjavaplayground.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.rxjavaplayground.data.mappers.ApiResponseMapper
import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository
import com.ngallazzi.rxjavaplayground.domain.usecases.GetDailyForecastsUseCase
import com.ngallazzi.rxjavaplayground.domain.usecases.GetWeatherUseCase
import com.ngallazzi.rxjavaplayground.ui.ForecastViewModel
import com.ngallazzi.rxjavaplayground.utils.Utils
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { Utils() }
    single { NetworkModule() }
    single { ApiResponseMapper(get()) }
    factory { NetworkModule().createWeatherApiRx() }
}

val viewModelsModule = module {
    factory { WeatherRemoteDataSourceRx(get(), get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    factory { GetWeatherUseCase(get()) }
    factory { GetDailyForecastsUseCase(get()) }

    viewModel {
        ForecastViewModel(get(), get())
    }
}