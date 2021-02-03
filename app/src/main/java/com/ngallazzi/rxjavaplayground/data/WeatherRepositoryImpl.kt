package com.ngallazzi.rxjavaplayground.data

import com.ngallazzi.rxjavaplayground.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.rxjavaplayground.data.entities.CityForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.domain.entities.City
import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository
import io.reactivex.rxjava3.core.Single

class WeatherRepositoryImpl(private val remoteDataSource: WeatherRemoteDataSourceRx) :
    WeatherRepository {
    override fun getCityWeather(city: String, apiKey: String): Single<CityForecast> {
        return remoteDataSource.getCityWeather(city, apiKey)
    }

    override fun getDailyForecasts(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): Single<List<DailyForecast>> {
        return remoteDataSource.getDailyForecasts(latitude, longitude, apiKey)
    }
}