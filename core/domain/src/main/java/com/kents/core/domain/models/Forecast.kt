package com.kents.core.domain.models


data class Forecast(
//    val cityName: String,
//    val state: String,
    val currentTemp: String,
    val dailyHighTemp: String,
    val precip: String,
    val time: String,
    val scale: String
)



//TODO model 5 day forcast off the "Properties" (heh) property on the forcase response.
//fun ForecastDTO.toModel(): Forecast = Forecast(
//    currentTemp = this.properties.periods
//)