package com.kents.core.domain

import com.kents.core.commons.logd
import com.kents.core.data.repositories.WeatherRepository
import com.kents.core.domain.models.Observation
import com.kents.core.domain.models.toModel
import javax.inject.Inject

class GetObservation @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(stationCode: String) : Result<Observation> {
        return weatherRepository.getObservation(stationCode).map { dto ->
            logd("ObservationDTO " + dto.properties.id)
            dto.toModel()
        }
    }
}