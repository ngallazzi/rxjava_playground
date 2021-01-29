package com.ngallazzi.rxjavaplayground.ui

import androidx.lifecycle.*
import com.ngallazzi.rxjavaplayground.domain.City
import com.ngallazzi.rxjavaplayground.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _city = MutableLiveData<City>()
    val city = _city

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            getWeatherUseCase.invoke(city, apiKey).subscribe(
                { response -> _city.postValue(response) },
                { e -> _error.postValue(e.message) }
            )
        }
    }

    class ForecastViewModelFactory(
        private val getWeatherUseCase: GetWeatherUseCase
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ForecastViewModel(
                getWeatherUseCase
            ) as T
        }
    }
}