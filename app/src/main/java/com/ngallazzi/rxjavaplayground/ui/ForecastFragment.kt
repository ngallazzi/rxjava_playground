package com.ngallazzi.rxjavaplayground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.ngallazzi.rxjavaplayground.databinding.FragmentForecastBinding
import com.ngallazzi.rxjavaplayground.entities.DailyForecast
import com.ngallazzi.rxjavaplayground.ui.adapters.forecastItemView
import com.ngallazzi.rxjavaplayground.ui.rxjava.ForecastViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private val forecasts = mutableListOf<DailyForecast>()

    private val forecastViewModel: ForecastViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forecastViewModel.getWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentForecastBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.city.observe(viewLifecycleOwner, { info ->
            binding.tvCityName.text = info.first.cityName
            binding.tvMinTemp.text = info.first.minTemp
            binding.tvWeather.text = info.first.forecastDesc
            binding.tvMaxTemp.text = info.first.maxTemp
            binding.ivCurrentWeather.load(info.first.iconUrl)
            forecasts.clear()
            forecasts.addAll(info.second)

            binding.ervNextDaysForecasts.withModels {
                forecasts.forEach {
                    forecastItemView {
                        id(it.hashCode())
                        date(it.date)
                        minTemp(it.temperatures.min)
                        maxTemp(it.temperatures.max)
                        icon(it.weather.iconUrl)
                    }
                }
            }

        })

        forecastViewModel.dataLoading.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    binding.clData.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
                false -> {
                    binding.clData.visibility = View.VISIBLE
                    binding.pbLoading.visibility = View.GONE
                }
            }
        })

        forecastViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}