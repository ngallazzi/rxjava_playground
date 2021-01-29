package com.ngallazzi.rxjavaplayground.data

import com.ngallazzi.rxjavaplayground.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.rxjavaplayground.domain.City
import com.ngallazzi.rxjavaplayground.domain.repositories.WeatherRepository
import io.reactivex.rxjava3.core.Single

class WeatherRepositoryImpl(private val remoteDataSource: WeatherRemoteDataSourceRx) :
    WeatherRepository {
    override fun getCityWeather(city: String, apiKey: String): Single<City> {
        return remoteDataSource.getCityWeather(city, apiKey)
    }
}