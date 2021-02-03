package com.ngallazzi.rxjavaplayground.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ngallazzi.rxjavaplayground.R
import com.ngallazzi.rxjavaplayground.ui.helpers.KotlinEpoxyHolder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@EpoxyModelClass(layout = R.layout.item_forecast)
abstract class ForecastModel : EpoxyModelWithHolder<ForecastModel.Holder>() {
    @EpoxyAttribute
    var date: LocalDateTime? = null

    @EpoxyAttribute
    var minTemp: String? = null

    @EpoxyAttribute
    var maxTemp: String? = null

    @EpoxyAttribute
    var iconUrl: String? = null

    override fun bind(holder: Holder) {
        holder.tvDate.text = formatter.format(date)
        holder.tvMaxTemp.text = maxTemp
        holder.tvMinTemp.text = minTemp
        holder.ivWeather.load(iconUrl)
    }

    class Holder : KotlinEpoxyHolder() {
        val tvDate by bind<TextView>(R.id.tvDate)
        val tvMinTemp by bind<TextView>(R.id.tvMinTemp)
        val tvMaxTemp by bind<TextView>(R.id.tvMaxTemp)
        val ivWeather by bind<ImageView>(R.id.ivWeather)
    }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("E dd")
    }
}