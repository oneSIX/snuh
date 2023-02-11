package com.kents.core.data.repositories

import com.kents.core.data.models.observationDto.ObservationDto
import com.kents.core.data.network.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getObservation(stationCode: String): Result<ObservationDto> =
        weatherService.getObservation(stationCode)
}