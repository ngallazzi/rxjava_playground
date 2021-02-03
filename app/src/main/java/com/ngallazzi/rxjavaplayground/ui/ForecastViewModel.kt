package com.ngallazzi.rxjavaplayground.ui

import androidx.lifecycle.*
import com.ngallazzi.rxjavaplayground.BuildConfig
import com.ngallazzi.rxjavaplayground.data.entities.CityForecast
import com.ngallazzi.rxjavaplayground.data.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.domain.entities.City
import com.ngallazzi.rxjavaplayground.domain.usecases.GetDailyForecastsUseCase
import com.ngallazzi.rxjavaplayground.domain.usecases.GetWeatherUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDailyForecastsUseCase: GetDailyForecastsUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _city: MutableLiveData<Pair<CityForecast, List<DailyForecast>>> = MutableLiveData()
    val city = _city

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeather(
        city: String = "Castellanza",
        latitude: Double = 45.6096,
        longitude: Double = 8.894,
        apiKey: String = BuildConfig.API_KEY
    ) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val getWeatherObservable = getWeatherUseCase.invoke(city, apiKey)
            val getDailyForecastsObservable =
                getDailyForecastsUseCase.invoke(latitude, longitude, apiKey)
            Single.zip(
                getWeatherObservable,
                getDailyForecastsObservable,
                { city, forecasts -> Pair(city, forecasts) }
            ).subscribe(
                { success ->
                    _dataLoading.postValue(false)
                    _city.postValue(success)
                },
                { error ->
                    _error.postValue(error.message)
                    _dataLoading.postValue(false)
                }
            )
        }
    }

    class ForecastViewModelFactory(
        private val getWeatherUseCase: GetWeatherUseCase,
        private val getDailyForecastsUseCase: GetDailyForecastsUseCase
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ForecastViewModel(
                getWeatherUseCase,
                getDailyForecastsUseCase
            ) as T
        }
    }
}