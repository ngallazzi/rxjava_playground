package com.ngallazzi.rxjavaplayground.data.entities

import java.time.LocalDateTime


class DailyForecast(
    val date: LocalDateTime,
    val temperature: DayTemperature,
    val weather: DailyWeather
)