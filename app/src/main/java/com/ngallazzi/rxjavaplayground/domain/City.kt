package com.ngallazzi.rxjavaplayground.domain

data class City(
    val id: String,
    val name: String,
    val weather: Weather,
    val weatherInfo: WeatherInfo
)