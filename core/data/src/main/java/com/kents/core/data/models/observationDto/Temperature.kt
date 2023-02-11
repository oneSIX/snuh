package com.kents.core.data.models.observationDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temperature(
  @SerialName("qualityControl")
  val qualityControl: String = "",
  @SerialName("unitCode")
  val unitCode: String = "",
  @SerialName("value")
  val value: Double? = 0.0
)