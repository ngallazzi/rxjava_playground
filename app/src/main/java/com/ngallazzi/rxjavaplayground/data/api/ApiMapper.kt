package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.domain.City
import com.ngallazzi.rxjavaplayground.domain.Weather
import io.reactivex.rxjava3.core.Single

class ApiResponseMapper {
    fun fromWeatherApiResponseToCity(response: WeatherApiResponse): City {
        val weather: Weather = response.weather.first()
        val info = response.weatherInfo
        return City(response.cityId, response.cityName, weather, info)
    }

    fun fromWeatherApiResponseToCity(response: Single<WeatherApiResponse>): Single<City> {
        return response.map {
            val weather = it.weather.first()
            weather.iconUrl = "${BuildConfig.IMAGES_URL}${weather.icon}@2x.png"

            City(
                it.cityId, it.cityName,
                it.weather.first(),
                it.weatherInfo
            )
        }
    }
}