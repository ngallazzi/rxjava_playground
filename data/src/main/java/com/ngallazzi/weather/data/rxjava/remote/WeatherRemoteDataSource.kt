package com.ngallazzi.weather.data.rxjava.remote

import com.ngallazzi.weather.data.rxjava.WeatherDataSource
import com.ngallazzi.weather.data.rxjava.api.WeatherApi
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import io.reactivex.rxjava3.core.Maybe

class WeatherRemoteDataSource(
    private val service: WeatherApi,
    private val apiKey: String
) : WeatherDataSource {

    override fun getCityWeather(cityName: String): Maybe<CurrentWeather> {
        return service.getCityWeather(cityName, apiKey)
    }

    override fun saveCityWeather(cityName: String, currentWeather: CurrentWeather) {
        TODO("Not yet implemented")
    }

    override fun getWeekWeather(coordinates: CurrentWeather.Coordinates): Maybe<WeekWeather> {
        return service.getWeekWeatherByCoordinates(coordinates.lat, coordinates.lon, appid = apiKey)
    }

    override fun saveWeekWeather(coordinates: CurrentWeather.Coordinates, weather: WeekWeather) {
        TODO("Not yet implemented")
    }
}