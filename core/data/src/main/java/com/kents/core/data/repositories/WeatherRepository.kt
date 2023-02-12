package com.kents.core.data.repositories

import com.kents.core.data.models.forecastdto.ForecastDTO
import com.kents.core.data.models.forecastdto.ForecastPointsDTO
import com.kents.core.data.models.observationdto.ObservationDTO
import com.kents.core.data.network.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getObservation(stationCode: String): Result<ObservationDTO> =
        weatherService.getObservation(stationCode)

    suspend fun getForecastUrl(geoCodes: String) : Result<ForecastPointsDTO> =
        weatherService.getForecastUrl(geoCodes)

    suspend fun getForecast(forecastUrl: String) : Result<ForecastDTO> =
        weatherService.getForecast(forecastUrl)
}