package com.ngallazzi.rxjavaplayground.domain.entities

data class City(
    val id: Int,
    val name: String,
    val weather: Weather,
    val weatherInfo: WeatherInfo
)