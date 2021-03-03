package com.ngallazzi.rxjavaplayground.ui.rxjava

import com.airbnb.mvrx.*
import com.ngallazzi.rxjavaplayground.WeatherApplication
import com.ngallazzi.rxjavaplayground.ui.DayForecastState
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.usecases.rxjava.GetDayWeatherUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate

class DayForecastViewModel(
    initialState: DayForecastState,
    private val getDayWeatherUseCase: GetDayWeatherUseCase
) :
    MvRxViewModel<DayForecastState>(initialState) {

    init {
        setState { copy(forecasts = Loading()) }
    }

    fun getDayForecast(latitude: Double, longitude: Double) {
        getDayWeatherUseCase.invoke(
            CurrentWeather.Coordinates(latitude, longitude),
            LocalDate.now()
        )
            .subscribeOn(Schedulers.io())
            .subscribe(
                { forecasts ->
                    setState { copy(forecasts = Success(forecasts)) }
                },
                { error ->
                    setState { copy(forecasts = Fail(error)) }
                }
            )
    }

    companion object : MvRxViewModelFactory<DayForecastViewModel, DayForecastState> {
        @JvmStatic
        override fun create(
            viewModelContext: ViewModelContext,
            state: DayForecastState
        ): DayForecastViewModel {
            val useCase: GetDayWeatherUseCase =
                viewModelContext.app<WeatherApplication>().getDayWeatherUseCase
            return DayForecastViewModel(state, useCase)
        }

    }
}