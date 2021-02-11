package com.ngallazzi.rxjavaplayground.entities

import java.time.LocalDateTime


data class DailyForecast(
    val date: LocalDateTime,
    val temperatures: Temperatures,
    val weather: Weather
) {
    class Weather(val id: Int, val iconUrl: String)

    class Temperatures(val day: String, val min: String, val max: String)
}