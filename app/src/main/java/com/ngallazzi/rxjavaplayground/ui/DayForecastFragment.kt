package com.ngallazzi.rxjavaplayground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.airbnb.mvrx.*
import com.ngallazzi.rxjavaplayground.Errors
import com.ngallazzi.rxjavaplayground.R
import com.ngallazzi.rxjavaplayground.Utils
import com.ngallazzi.rxjavaplayground.databinding.FragmentDayForecastBinding
import com.ngallazzi.rxjavaplayground.ui.adapters.dayForecastItemView
import com.ngallazzi.rxjavaplayground.ui.rxjava.DayForecastViewModel
import java.time.format.DateTimeFormatter

class DayForecastFragment : BaseMvRxFragment() {
    private val dayForecastViewModel: DayForecastViewModel by fragmentViewModel()
    private lateinit var binding: FragmentDayForecastBinding
    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDayForecastBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val latitude = it.getDouble(getString(R.string.latitude_bundle_id))
            val longitude = it.getDouble(getString(R.string.longitude_bundle_id))
            binding.tvCityName.text = it.getString(getString(R.string.city_name_id))
            dayForecastViewModel.getDayForecast(latitude, longitude)
        } ?: kotlin.run {
            Toast.makeText(
                requireContext(),
                getString(Errors.NO_COORDINATES_ARGUMENT_PROVIDED.id),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun invalidate() {
        withState(dayForecastViewModel) { state ->
            when (state.forecasts) {
                // 1
                is Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.clData.visibility = View.GONE
                }
                // 2
                is Success -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.clData.visibility = View.VISIBLE

                    val forecasts = state.forecasts.invoke()
                    binding.tvCurrentDate.text = formatter.format(forecasts.first().date)
                    binding.ivCurrentWeather.load(forecasts.first().iconUrl)

                    binding.ervHourlyForcecast.withModels {
                        forecasts.forEach {
                            dayForecastItemView {
                                id(it.hashCode())
                                weatherImage(it.iconUrl)
                                date(it.date)
                                temperature(it.temperature)
                                weather(it.weather)
                            }
                        }
                    }
                }
                // 3
                is Fail -> {
                    Toast.makeText(
                        requireContext(),
                        getString(Errors.GENERIC_NETWORK_ERROR.id, state.forecasts.error.message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }
}