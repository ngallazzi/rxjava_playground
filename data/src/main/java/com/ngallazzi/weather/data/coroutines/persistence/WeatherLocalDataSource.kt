package com.ngallazzi.weather.data.coroutines.persistence

import com.ngallazzi.weather.data.coroutines.WeatherDataSource
import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather

class WeatherLocalDataSource : WeatherDataSource {
    // in memory persistence for simplicity
    private val citiesCurrentWeathers: ArrayList<Pair<String, CurrentWeather>> = arrayListOf()
    private val citiesWeekWeathers: ArrayList<Pair<CurrentWeather.Coordinates, WeekWeather>> =
        arrayListOf()

    override suspend fun getCityWeather(cityName: String): Result<CurrentWeather> {
        citiesCurrentWeathers.find { it.first == cityName }?.let {
            return Result.Success(it.second)
        } ?: kotlin.run {
            return Result.Error(Exception("city weather cache not found"))
        }
    }

    override fun saveCityWeather(cityName: String, currentWeather: CurrentWeather) {
        citiesCurrentWeathers.find { it.first == cityName }?.let {
            it.second.weatherInfo = currentWeather.weatherInfo
            it.second.weather = currentWeather.weather
        } ?: kotlin.run {
            citiesCurrentWeathers.add(Pair(cityName, currentWeather))
        }
    }

    override suspend fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Result<WeekWeather> {
        citiesWeekWeathers.find { it.first.lon == coordinates.lon && it.first.lat == coordinates.lat }
            ?.let { weather ->
                return Result.Success(weather.second)
            } ?: kotlin.run {
            return Result.Error(Exception("week weather cache not found"))
        }
    }

    override fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) {
        citiesWeekWeathers.find { it.first.lat == coordinates.lat && it.first.lon == coordinates.lon }
            ?.let {
                it.second.dayForecasts = weather.dayForecasts
            } ?: kotlin.run {
            citiesWeekWeathers.add(Pair(coordinates, weather))
        }
    }
}