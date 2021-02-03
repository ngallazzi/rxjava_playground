package com.ngallazzi.rxjavaplayground.data.entities

data class DailyWeather(
    val id: Int,
    val main: String,
    val description: String,
    val iconUrl: String
)