package com.kents.core.data.models.observationdto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObservationDTO(
  @SerialName("id")
  val id: String = "",
  @SerialName("properties")
  val properties: Properties = Properties(),
  @SerialName("type")
  val type: String = ""
)

@Serializable
data class Properties(
  @SerialName("icon")
  val icon: String = "",
  @SerialName("@id")
  val id: String = "",
  @SerialName("station")
  val station: String = "",
  @SerialName("temperature")
  val temperature: Temperature = Temperature(),
  @SerialName("textDescription")
  val textDescription: String = "",
  @SerialName("timestamp")
  val timestamp: String = "",
  @SerialName("@type")
  val type: String = "",
)

@Serializable
data class Temperature(
  @SerialName("qualityControl")
  val qualityControl: String = "",
  @SerialName("unitCode")
  val unitCode: String = "",
  @SerialName("value")
  val value: Double? = 0.0
)