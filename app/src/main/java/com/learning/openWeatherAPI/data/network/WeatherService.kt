package com.learning.openWeatherAPI.data.network


import com.learning.openWeatherAPI.core.RetrofitHelper
import com.learning.openWeatherAPI.data.model.FiveDayForecastModel
import com.learning.openWeatherAPI.data.model.OneDayForecastModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


class WeatherService @Inject constructor(
    private val apiClient: WeatherApiClient
) {

    private val api_key: String
        get() = "a799893b55f2348fc307f48aa622e1e4"

    suspend fun getOneDayForecast(): OneDayForecastModel{
        return withContext(Dispatchers.IO) {
            val response: Response<OneDayForecastModel> = apiClient.getOneDayForecast(api_key)

            response.body() ?: OneDayForecastModel()
        }
    }

    suspend fun getFiveDayForecast(): FiveDayForecastModel {
        return withContext(Dispatchers.IO) {
            val response: Response<FiveDayForecastModel> = apiClient.getFiveDayForecast(api_key)

            response.body() ?: FiveDayForecastModel()
        }
    }
}