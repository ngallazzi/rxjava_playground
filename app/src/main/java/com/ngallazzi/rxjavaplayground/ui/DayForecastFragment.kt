package com.ngallazzi.rxjavaplayground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.*
import com.ngallazzi.rxjavaplayground.Errors
import com.ngallazzi.rxjavaplayground.R
import com.ngallazzi.rxjavaplayground.databinding.FragmentDayForecastBinding
import com.ngallazzi.rxjavaplayground.ui.rxjava.DayForecastViewModel

class DayForecastFragment : BaseMvRxFragment() {
    private val dayForecastViewModel: DayForecastViewModel by fragmentViewModel()
    private lateinit var binding: FragmentDayForecastBinding

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
                    binding.ervHourlyForcecast.visibility = View.GONE
                }
                // 2
                is Success -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.ervHourlyForcecast.visibility = View.VISIBLE
                    // TODO set adapter here
                    val forecasts = state.forecasts.invoke()

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
}