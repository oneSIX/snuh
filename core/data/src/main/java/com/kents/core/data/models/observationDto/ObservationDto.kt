package com.kents.core.data.models.observationDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObservationDto(
  @SerialName("id")
  val id: String = "",
  @SerialName("properties")
  val properties: Properties = Properties(),
  @SerialName("type")
  val type: String = ""
)