package com.ngallazzi.rxjavaplayground.data.api

import com.ngallazzi.rxjavaplayground.data.entities.CityForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.data.mappers.ApiResponseMapper
import io.reactivex.rxjava3.core.Single

class WeatherRemoteDataSourceRx(
    private val service: WeatherApiRx,
    private val mapper: ApiResponseMapper
) {
    fun getCityWeather(cityName: String, apiKey: String): Single<CityForecast> {
        val observable = service.getCityWeather(cityName, apiKey)
        return observable.flatMap { mapper.fromWeatherApiResponseToCityForecast(observable) }
    }

    fun getDailyForecasts(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Single<List<DailyForecast>> {
        val observable = service.getDailyWeather(latitude, longitude, appid = apiKey)
        return observable.flatMap { mapper.fromDailyWeatherApiResponseToDayForecastList(observable) }
    }
}