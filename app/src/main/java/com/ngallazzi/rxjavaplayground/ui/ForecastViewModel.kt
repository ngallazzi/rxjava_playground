package com.ngallazzi.rxjavaplayground.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.entities.CityForecast
import com.ngallazzi.rxjavaplayground.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.mappers.ForecastsMapper
import com.ngallazzi.weather.domain.usecases.GetDailyForecastsUseCase
import com.ngallazzi.weather.domain.usecases.GetWeatherUseCase
import io.reactivex.rxjava3.core.Single

class ForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDailyForecastsUseCase: GetDailyForecastsUseCase,
    private val mapper: ForecastsMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _city: MutableLiveData<Pair<CityForecast, List<DailyForecast>>> = MutableLiveData()
    val city = _city

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeather(
        city: String = "Castellanza"
    ) {
        _dataLoading.postValue(true)
        val weatherObservable = getWeatherUseCase.invoke(city)
        val dailyForecastsObservable = weatherObservable.flatMap { weather ->
            getDailyForecastsUseCase.invoke(
                weather.coordinates.lat,
                weather.coordinates.lon
            )
        }

        Single.zip(weatherObservable, dailyForecastsObservable,
            { weather, dailyForecast -> Pair(weather, dailyForecast) })
            .subscribe(
                { success ->
                    val cityForecast = mapper.fromCurrentWeatherToCityForecast(
                        success.first,
                        BuildConfig.IMAGES_URL
                    )
                    val dailyForecast = mapper.fromWeekWeatherToDailyForecastList(
                        success.second,
                        BuildConfig.IMAGES_URL
                    )
                    _city.postValue(Pair(cityForecast, dailyForecast))
                    _dataLoading.postValue(false)
                },
                { error ->
                    _error.postValue(error.message)
                    _dataLoading.postValue(false)
                }
            )
    }
}
