package com.kents.core.domain.models

import com.kents.core.data.models.forecastdto.ForecastDTO

data class ForecastPeriod(
    val forecastName: String,
    val detailedForecast: String,
    val startTime: String,
    val temperature: Int,
    val temperatureUnit: String,
    val iconUrl: String
)

fun ForecastDTO.toModel(): List<ForecastPeriod>  {

    // iterate over all of the properties ForecastDTO.properties.periods and turn them into ForecastPeriods
    val forecastList = mutableListOf<ForecastPeriod>()
    this.properties.periods.forEach { period ->

        forecastList.add(

            ForecastPeriod(
                forecastName = period.name,
                detailedForecast = period.detailedForecast,
                startTime = period.startTime,
                temperature = period.temperature,
                temperatureUnit = period.temperatureUnit,
                iconUrl = period.icon
            )
        )
    }

    return forecastList
}
