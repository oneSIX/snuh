package com.kents.core.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
  @SerialName("id")
  val id: String = "",
  @SerialName("properties")
  val properties: ForecastProperties = ForecastProperties(),
  @SerialName("type")
  val type: String = ""
)

@Serializable
data class ForecastProperties(
  @SerialName("elevation")
  val elevation: Elevation = Elevation(),
  @SerialName("forecastGenerator")
  val forecastGenerator: String = "",
  @SerialName("generatedAt")
  val generatedAt: String = "",
  @SerialName("geometry")
  val geometry: String = "",
  @SerialName("periods")
  val periods: List<Period> = listOf(),
  @SerialName("units")
  val units: String = "",
  @SerialName("updateTime")
  val updateTime: String = ""
)

@Serializable
data class Period(
  @SerialName("detailedForecast")
  val detailedForecast: String = "",
  @SerialName("endTime")
  val endTime: String = "",
  @SerialName("isDaytime")
  val isDaytime: Boolean = false,
  @SerialName("name")
  val name: String = "",
  @SerialName("number")
  val number: Int = 0,
  @SerialName("shortForecast")
  val shortForecast: String = "",
  @SerialName("startTime")
  val startTime: String = "",
  @SerialName("temperatureTrend")
  val temperatureTrend: String = "",
  @SerialName("windDirection")
  val windDirection: String = ""
)

@Serializable
data class Elevation(
  @SerialName("maxValue")
  val maxValue: Int = 0,
  @SerialName("minValue")
  val minValue: Int = 0,
  @SerialName("qualityControl")
  val qualityControl: String = "",
  @SerialName("unitCode")
  val unitCode: String = "",
  @SerialName("value")
  val value: Int = 0
)