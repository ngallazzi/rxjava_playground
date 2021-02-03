package com.ngallazzi.rxjavaplayground.data.mappers

import com.ngallazzi.rxjavaplayground.data.entities.CityForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyWeather
import com.ngallazzi.rxjavaplayground.data.entities.DayTemperature
import com.ngallazzi.rxjavaplayground.domain.CurrentWeatherApiResponse
import com.ngallazzi.rxjavaplayground.domain.DailyWeatherApiResponse
import com.ngallazzi.rxjavaplayground.domain.entities.City
import com.ngallazzi.rxjavaplayground.domain.entities.Weather
import com.ngallazzi.rxjavaplayground.domain.entities.WeatherInfo
import com.ngallazzi.rxjavaplayground.utils.Utils
import io.reactivex.rxjava3.core.Single
import java.time.LocalDateTime
import java.time.ZoneOffset

class ApiResponseMapper(private val utils: Utils) {
    fun fromWeatherApiResponseToCityForecast(response: CurrentWeatherApiResponse): City {
        val weather: Weather = response.weather.first()
        val info = response.weatherInfo
        return City(response.cityId, response.cityName, weather, info)
    }

    fun fromWeatherApiResponseToCityForecast(response: Single<CurrentWeatherApiResponse>): Single<CityForecast> {
        return response.map { payload ->
            CityForecast(
                payload.cityId,
                payload.cityName,
                utils.getFormattedTemperature(payload.weatherInfo.minTemp),
                utils.getFormattedTemperature(payload.weatherInfo.maxTemp),
                payload.weather.first().description,
                utils.fromIconIdToImageUrl(payload.weather.first().icon)
            )
        }
    }

    fun fromDailyWeatherApiResponseToDayForecastList(response: Single<DailyWeatherApiResponse>): Single<List<DailyForecast>> {
        return response.map {
            val dailyForecastsList = arrayListOf<DailyForecast>()
            for (forecast in it.dayForecasts) {
                val weather = forecast.weather.first()
                val dailyWeather = DailyWeather(
                    weather.id,
                    weather.main,
                    weather.description,
                    utils.fromIconIdToImageUrl(weather.icon)
                )

                dailyForecastsList.add(
                    DailyForecast(
                        LocalDateTime.ofEpochSecond(forecast.date, 0, ZoneOffset.UTC),
                        DayTemperature(
                            utils.getFormattedTemperature(forecast.temperature.day),
                            utils.getFormattedTemperature(forecast.temperature.min),
                            utils.getFormattedTemperature(forecast.temperature.max),
                        ),
                        dailyWeather
                    )
                )
            }
            dailyForecastsList
        }
    }
}