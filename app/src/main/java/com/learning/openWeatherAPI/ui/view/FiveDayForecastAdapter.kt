package com.learning.openWeatherAPI.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.openWeatherAPI.data.model.OneDayForecastModel
import com.learning.openWeatherAPI.databinding.DayForecastItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class FiveDayForecastAdapter : RecyclerView.Adapter<FiveDayForecastAdapter.FiveDayViewHolder>() {


    var daysForecasted = mutableListOf<OneDayForecastModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiveDayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DayForecastItemBinding.inflate(inflater,parent,false)
        return FiveDayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiveDayViewHolder, position: Int) {
        val dayForecasted = daysForecasted[position]

        val dateTime = SimpleDateFormat("dd/MM/yyyy HH:mm").format(dayForecasted.time.toInt() * 1000L)

        holder.binding.apply {
            timestampTv.text = "Дата: ${dateTime}"
            forecastSkyTv.text = "Облачность: ${dayForecasted.weather[0].weatherDesc}"
            forecastTempTv.text = "Температура: ${dayForecasted.main.temp} ºC"
            forecastWindTv.text = "Скорость ветра: ${(dayForecasted.wind.windSpeed * 3.6).roundToInt()} км/ч"
            forecastHumidityTv.text = "Влажность: ${dayForecasted.main.humidity}%"
        }
    }

    fun setFiveDayForecastList(daysForecasted: List<OneDayForecastModel>) {
        this.daysForecasted = daysForecasted.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return daysForecasted.size
    }

    class FiveDayViewHolder(val binding: DayForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}