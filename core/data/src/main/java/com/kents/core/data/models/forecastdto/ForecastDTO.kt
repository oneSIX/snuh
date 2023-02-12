package com.kents.core.data.models.forecastdto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Generated via the JSON to data class plugin via the JSON
 * response from this URL: https://api.weather.gov/gridpoints/EAX/50,61/forecast
 *
 * the important part is the properties.periods list which contains forecast info (start/end etc.)
 */
@Serializable
data class ForecastDTO(
    @SerialName("properties")
    val properties: ForecastProperties = ForecastProperties(),
    @SerialName("type")
    val type: String = ""
)
@Serializable
data class ForecastProperties(
    @SerialName("forecastGenerator")
    val forecastGenerator: String = "",
    @SerialName("generatedAt")
    val generatedAt: String = "",
    @SerialName("periods")
    val periods: List<Period> = listOf(), //Here we go!
    @SerialName("units")
    val units: String = "",
    @SerialName("updateTime")
    val updateTime: String = "",
    @SerialName("updated")
    val updated: String = "",
    @SerialName("validTimes")
    val validTimes: String = ""
)

@Serializable
data class Period(
    @SerialName("detailedForecast")
    val detailedForecast: String = "",
    @SerialName("endTime")
    val endTime: String = "",
    @SerialName("icon")
    val icon: String = "",
    @SerialName("isDaytime")
    val isDaytime: Boolean = false,
    @SerialName("name")
    val name: String = "",
    @SerialName("number")
    val number: Int = 0,
    @SerialName("probabilityOfPrecipitation")
    val probabilityOfPrecipitation: ProbabilityOfPrecipitation? = ProbabilityOfPrecipitation(),
    @SerialName("shortForecast")
    val shortForecast: String = "",
    @SerialName("startTime")
    val startTime: String = "",
    @SerialName("temperature")
    val temperature: Int = 0,
    @SerialName("temperatureTrend")
    val temperatureTrend: String? = "",
    @SerialName("temperatureUnit")
    val temperatureUnit: String = "",
    @SerialName("windDirection")
    val windDirection: String = "",
    @SerialName("windSpeed")
    val windSpeed: String = ""
)

@Serializable
data class ProbabilityOfPrecipitation(
    @SerialName("unitCode")
    val unitCode: String = "",
    @SerialName("value")
    val value: Int? = 0
)

