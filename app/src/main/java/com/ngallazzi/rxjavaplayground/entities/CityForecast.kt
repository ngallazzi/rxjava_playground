package com.ngallazzi.rxjavaplayground.entities

import com.ngallazzi.weather.domain.entities.CurrentWeather

class CityForecast(
    val id: Int, val cityName: String, val minTemp: String, val maxTemp: String,
    val coordinates: CurrentWeather.Coordinates,
    val forecastDesc: String,
    val iconUrl: String
)