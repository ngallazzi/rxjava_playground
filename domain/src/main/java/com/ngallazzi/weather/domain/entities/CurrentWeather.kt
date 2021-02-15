package com.ngallazzi.weather.domain.entities

import com.squareup.moshi.Json

data class CurrentWeather(
    @Json(name = "coord") val coordinates: Coordinates,
    @Json(name = "weather") val weather: List<Weather>,
    @Json(name = "main") val weatherInfo: WeatherInfo,
    @Json(name = "id") val cityId: Int,
    @Json(name = "name") val cityName: String
) {
    class Coordinates(val lon: Double, val lat: Double)

    class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )

    class WeatherInfo(
        val temp: Double,
        @Json(name = "temp_min")
        val minTemp: Double,
        @Json(name = "temp_max")
        val maxTemp: Double
    )
}