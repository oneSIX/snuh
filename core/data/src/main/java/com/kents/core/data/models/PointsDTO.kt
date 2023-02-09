package com.kents.core.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PointsDTO(
  @SerialName("id")
  val id: String = "",
  @SerialName("properties")
  val properties: Properties = Properties(),
  @SerialName("type")
  val type: String = ""
)

@Serializable
data class Properties(
//  @SerialName("county")
//  val county: String = "",
//  @SerialName("cwa")
//  val cwa: String = "",
//  @SerialName("fireWeatherZone")
//  val fireWeatherZone: String = "",
  @SerialName("forecast")
  val forecast: String = "",
//  @SerialName("forecastGridData")
//  val forecastGridData: String = "",
//  @SerialName("forecastHourly")
//  val forecastHourly: String = "",
//  @SerialName("forecastOffice")
//  val forecastOffice: String = "",
//  @SerialName("forecastZone")
//  val forecastZone: String = "",
//  @SerialName("geometry")
//  val geometry: String = "",
//  @SerialName("gridId")
//  val gridId: String = "",
//  @SerialName("gridX")
//  val gridX: Int = 0,
//  @SerialName("gridY")
//  val gridY: Int = 0,
//  @SerialName("@id")
//  val id: String = "",
//  @SerialName("observationStations")
//  val observationStations: String = "",
//  @SerialName("radarStation")
//  val radarStation: String = "",
//  @SerialName("timeZone")
//  val timeZone: String = "",
//  @SerialName("@type")
//  val type: String = ""
)