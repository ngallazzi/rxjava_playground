package com.ngallazzi.rxjavaplayground.mappers

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.entities.CityForecast
import com.ngallazzi.rxjavaplayground.entities.DailyForecast
import com.ngallazzi.weather.domain.entities.CurrentWeather
import com.ngallazzi.weather.domain.entities.WeekWeather
import java.time.LocalDateTime
import java.time.ZoneOffset

class ForecastsMapper(private val utils: Utils) {
    fun fromCurrentWeatherToCityForecast(
        response: CurrentWeather,
        baseImageUrl: String = BuildConfig.IMAGES_URL
    ): CityForecast {
        return CityForecast(
            response.cityId,
            response.cityName,
            utils.getFormattedTemperature(response.weatherInfo.minTemp),
            utils.getFormattedTemperature(response.weatherInfo.maxTemp),
            response.coordinates,
            response.weather.first().description,
            utils.fromIconIdToImageUrl(response.weather.first().icon, baseImageUrl)
        )
    }

    fun fromWeekWeatherToDailyForecastList(
        response: WeekWeather,
        baseImageUrl: String = BuildConfig.IMAGES_URL
    ): List<DailyForecast> {
        val dailyForecastsList = arrayListOf<DailyForecast>()
        for (forecast in response.dayForecasts) {
            val date = LocalDateTime.ofEpochSecond(forecast.date, 0, ZoneOffset.UTC)
            val weather = forecast.weather.first()
            val weatherWithIconUrl = DailyForecast.Weather(
                weather.id,
                utils.fromIconIdToImageUrl(weather.icon, baseImageUrl)
            )

            val dailyForecast = DailyForecast(
                date,
                DailyForecast.Temperatures(
                    utils.getFormattedTemperature(forecast.temperature.day),
                    utils.getFormattedTemperature(forecast.temperature.min),
                    utils.getFormattedTemperature(forecast.temperature.max)
                ),
                weatherWithIconUrl
            )

            dailyForecastsList.add(dailyForecast)
        }
        return dailyForecastsList
    }
}