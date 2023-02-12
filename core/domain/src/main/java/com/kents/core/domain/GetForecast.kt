package com.kents.core.domain

import com.kents.core.commons.logd
import com.kents.core.data.repositories.WeatherRepository
import com.kents.core.domain.models.Forecast
import com.kents.core.domain.models.toModel
import javax.inject.Inject


class GetForecast @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(geoCodes: String): Result<Forecast> {
        val result = weatherRepository.getForecastUrl(geoCodes)
        return if (result.isSuccess) {
            // make another API call with this successful URL
            // getForecast returns the correct type that we can properly model.
            result.map { forecastPointDTO ->
                return weatherRepository.getForecast(forecastPointDTO.properties.forecast)
                    .map { forecastDTO ->
                        forecastDTO.toModel()
                    }
            }
        } else {
            return result.map {
                // ignored Forecast object.  wish I had a better way to map from one type to a
                // failure of another but this will have to do for now - You have to map the
                // positive side even though we have a Result that is a failure.
                logd("Get Forecast URL failure")
                Forecast()
            }
        }
    }
}




