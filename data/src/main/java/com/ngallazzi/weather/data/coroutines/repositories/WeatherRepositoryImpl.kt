package com.ngallazzi.weather.data.coroutines.repositories


import com.ngallazzi.weather.data.coroutines.persistence.WeatherLocalDataSource
import com.ngallazzi.weather.data.coroutines.remote.WeatherRemoteDataSource
import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import com.ngallazzi.weather.domain.repositories.coroutines.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) :
    WeatherRepository {
    override suspend fun getCurrentWeather(city: String, forceUpdate: Boolean): Result<CurrentWeather> {
        return when (forceUpdate) {
            true -> {
                val result = remoteDataSource.getCityWeather(city)
                if (result is Result.Success) {
                    localDataSource.saveCityWeather(city, result.data)
                }
                result
            }
            false -> localDataSource.getCityWeather(city)
        }
    }

    override suspend fun getWeekWeather(
        coordinates: CurrentWeather.Coordinates,
        forceUpdate: Boolean
    ): Result<WeekWeather> {
        return when (forceUpdate) {
            true -> {
                val result = remoteDataSource.getWeekWeather(coordinates)
                if (result is Result.Success) {
                    localDataSource.saveWeekWeather(coordinates, result.data)
                }
                result
            }
            false -> localDataSource.getWeekWeather(coordinates)
        }
    }
}