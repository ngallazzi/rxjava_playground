package com.ngallazzi.rxjavaplayground.ui.rxjava

import com.airbnb.mvrx.*
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.WeatherApplication
import com.ngallazzi.rxjavaplayground.mappers.DayForecastsMapper
import com.ngallazzi.rxjavaplayground.ui.DayForecastState
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.usecases.rxjava.GetDayWeatherUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalTime.MIDNIGHT

class DayForecastViewModel(
    initialState: DayForecastState,
    private val getDayWeatherUseCase: GetDayWeatherUseCase,
    private val mapper: DayForecastsMapper
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
                    val mappedForecasts = mapper.fromHourlyListToHourlyForecastList(forecasts)
                    val tomorrow = LocalDate.now().plusDays(1).atStartOfDay()
                    mappedForecasts.filter { it.date.isBefore(tomorrow) }.let { hourlyForecasts ->
                        setState {
                            copy(
                                forecasts = Success(
                                    hourlyForecasts
                                )
                            )
                        }
                    }
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
            state: DayForecastState,
        ): DayForecastViewModel {
            val useCase: GetDayWeatherUseCase =
                viewModelContext.app<WeatherApplication>().getDayWeatherUseCase
            val mapper = DayForecastsMapper(Utils())
            return DayForecastViewModel(state, useCase, mapper)
        }

    }
}