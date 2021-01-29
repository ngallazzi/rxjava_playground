package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.domain.City
import io.reactivex.rxjava3.core.Single

class WeatherRemoteDataSourceRx(
    private val service: WeatherApiRx,
    private val mapper: ApiResponseMapper
) {
    fun getCityWeather(cityName: String, apiKey: String): Single<City> {
        val observable = service.getCityWeather(cityName, apiKey)
        return observable.flatMap { mapper.fromWeatherApiResponseToCity(observable) }
    }
}