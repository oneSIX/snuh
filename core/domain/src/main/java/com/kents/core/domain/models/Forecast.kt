package com.kents.core.domain.models

import com.kents.core.data.models.forecastdto.ForecastDTO


data class Forecast(
    val forecasts: List<ForecastPeriod> = listOf(),
    val timeStamp: String = "",
)

data class ForecastPeriod(
    val detailedForecast: String,
    val startTime: String,
    val temperature: Int,
    val temperatureUnit: String,
    val precipitationProbability : String,
    val iconUrl: String
)

fun ForecastDTO.toModel(): Forecast  {

    // iterate over all of the properties ForecastDTO.properties.periods and turn them into ForecastPeriods
    val forecastList = mutableListOf<ForecastPeriod>()
    this.properties.periods.forEach { period ->
        forecastList.add(
            ForecastPeriod(
                detailedForecast = period.detailedForecast,
                startTime = period.startTime,
                temperature = period.temperature,
                temperatureUnit = period.temperatureUnit,
                precipitationProbability = period.probabilityOfPrecipitation?.value.toString(),
                iconUrl = period.icon
            )
        )
    }

    return Forecast(
        timeStamp = this.properties.updateTime,
        forecasts = forecastList
    )

    //TODO pull temp parsing into it's own helper since it's going to be used here + on the Observation models.
}
