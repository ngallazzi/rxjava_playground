package com.ngallazzi.weather.data

import com.ngallazzi.weather.data.api.WeatherRemoteDataSourceRx
import com.ngallazzi.weather.data.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import com.ngallazzi.weather.domain.repositories.WeatherRepository
import io.reactivex.rxjava3.core.Single

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSourceRx,
    private val localDataSource: WeatherLocalDataSource
) :
    WeatherRepository {
    override fun getCurrentWeather(city: String, forceUpdate: Boolean): Single<CurrentWeather> {
        return when (forceUpdate) {
            true -> remoteDataSource.getCityWeather(city)
            false -> Single.just(localDataSource.getCurrentWeather(city))
        }
    }

    override fun getWeekWeather(
        latitude: Double,
        longitude: Double,
        forceUpdate: Boolean
    ): Single<WeekWeather> {
        return when (forceUpdate) {
            true -> {
                val weekWeather = remoteDataSource.getWeekWeather(latitude, longitude)
                weekWeather.subscribe { success -> localDataSource.saveWeekWeather(success) }
                weekWeather
            }
            false -> Single.just(localDataSource.getWeekWeather())
        }
    }
}