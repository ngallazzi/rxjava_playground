package com.ngallazzi.weather.data.rxjava.repositories

import com.ngallazzi.weather.data.rxjava.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.data.rxjava.remote.WeatherRemoteDataSource
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import com.ngallazzi.weather.domain.repositories.rxjava.WeatherRepository
import io.reactivex.rxjava3.core.Maybe

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) :
    WeatherRepository {
    override fun getCurrentWeather(city: String, forceUpdate: Boolean): Maybe<CurrentWeather> {
        return when (forceUpdate) {
            true -> remoteDataSource.getCityWeather(city)
            false -> localDataSource.getCityWeather(city)
        }
    }

    override fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        forceUpdate: Boolean
    ): Maybe<WeekWeather> {
        return when (forceUpdate) {
            true -> remoteDataSource.getWeekWeather(coordinates)
            false -> localDataSource.getWeekWeather(coordinates)
        }
    }

    override fun saveCityWeather(city: String, weather: CurrentWeather) {
        localDataSource.saveCityWeather(city, weather)
    }

    override fun saveWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        weekWeather: WeekWeather
    ) {
        localDataSource.saveWeekWeather(coordinates, weekWeather)
    }
}