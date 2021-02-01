package com.ngallazzi.rxjavaplayground.domain

data class City(
    val id: Int,
    val name: String,
    val weather: Weather,
    val weatherInfo: WeatherInfo
)