package com.ngallazzi.rxjavaplayground.ui

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ngallazzi.rxjavaplayground.entities.HourlyForecast
import com.ngallazzi.weather.domain.entities.DayWeather
import com.ngallazzi.weather.domain.entities.Hourly

data class DayForecastState(val forecasts: Async<List<HourlyForecast>> = Uninitialized) : MvRxState