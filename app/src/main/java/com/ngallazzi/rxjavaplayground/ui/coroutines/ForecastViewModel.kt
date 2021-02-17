package com.ngallazzi.rxjavaplayground.ui.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.entities.CityForecast
import com.ngallazzi.rxjavaplayground.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.mappers.ForecastsMapper
import com.ngallazzi.weather.domain.common.Result
import com.ngallazzi.weather.domain.usecases.coroutines.GetWeatherUseCase
import com.ngallazzi.weather.domain.usecases.coroutines.GetWeekWeatherUseCase
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeekWeatherUseCase: GetWeekWeatherUseCase,
    private val mapper: ForecastsMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _city: MutableLiveData<Pair<CityForecast, List<DailyForecast>>> = MutableLiveData()
    val city = _city

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeather(
        city: String = "Castellanza",
        forceUpdate: Boolean = false
    ) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val weatherResult = getWeatherUseCase.invoke(city, forceUpdate)) {
                is Result.Success -> {
                    when (val weekResult =
                        getWeekWeatherUseCase.invoke(weatherResult.data.coordinates, forceUpdate)) {
                        is Result.Success -> {
                            val cityForecast = mapper.fromCurrentWeatherToCityForecast(
                                weatherResult.data,
                                BuildConfig.IMAGES_URL
                            )

                            val dailyForecast = mapper.fromWeekWeatherToDailyForecastList(
                                weekResult.data,
                                BuildConfig.IMAGES_URL
                            )

                            _city.postValue(Pair(cityForecast, dailyForecast))
                        }
                        is Result.Error -> {
                            _error.postValue(weekResult.exception.message)
                            _dataLoading.postValue(false)
                        }
                    }
                    _dataLoading.postValue(false)
                }
                is Result.Error -> {
                    _error.postValue(weatherResult.exception.message)
                    _dataLoading.postValue(false)
                }
            }
        }
    }

}
