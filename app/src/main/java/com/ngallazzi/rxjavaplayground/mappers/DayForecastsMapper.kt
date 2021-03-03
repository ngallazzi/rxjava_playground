package com.ngallazzi.rxjavaplayground.mappers

import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.entities.HourlyForecast
import com.ngallazzi.weather.domain.entities.Hourly

class DayForecastsMapper(private val utils: Utils) {
    fun fromHourlyListToHourlyForecastList(
        forecasts: List<Hourly>,
        baseImageUrl: String = BuildConfig.IMAGES_URL
    ): List<HourlyForecast> {
        val hourlyForecasts = arrayListOf<HourlyForecast>()
        for (forecast in forecasts) {
            hourlyForecasts.add(
                HourlyForecast(
                    utils.getLocalDateTimeFromTimeStamp(forecast.date),
                    utils.fromIconIdToImageUrl(forecast.weather.first().icon, baseImageUrl),
                    utils.getFormattedTemperature(forecast.temperature),
                    forecast.weather.first().main
                )
            )
        }
        return hourlyForecasts
    }
}