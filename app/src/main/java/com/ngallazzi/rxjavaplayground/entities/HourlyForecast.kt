package com.ngallazzi.rxjavaplayground.entities

import java.time.LocalDateTime


data class HourlyForecast(
    val date: LocalDateTime,
    val iconUrl: String,
    val temperature: String,
    val weather: String
)