package com.kents.core.data.models.observationDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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