package com.kents.core.data.models.forecastdto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Generated via the JSON to data class plugin via the JSON response
 * from this URL: https://api.weather.gov/points/39.3528,-94.3711
 */
@Serializable
data class ForecastPointsDTO(
//  @SerialName("@context")
//  val context: List<Any> = listOf(),
//  @SerialName("geometry")
//  val geometry: Geometry = Geometry(),
  @SerialName("id")
  val id: String = "",
  @SerialName("properties")
  val properties: Properties = Properties(),
  @SerialName("type")
  val type: String = ""
) {
//  @Serializable
//  data class Geometry(
//    @SerialName("coordinates")
//    val coordinates: List<Double> = listOf(),
//    @SerialName("type")
//    val type: String = ""
//  )

  @Serializable
  data class Properties(
//    @SerialName("county")
//    val county: String = "",
//    @SerialName("cwa")
//    val cwa: String = "",
//    @SerialName("fireWeatherZone")
//    val fireWeatherZone: String = "",
    @SerialName("forecast") //This is what we are here for.
    val forecast: String = "",
//    @SerialName("forecastGridData")
//    val forecastGridData: String = "",
//    @SerialName("forecastHourly")
//    val forecastHourly: String = "",
//    @SerialName("forecastOffice")
//    val forecastOffice: String = "",
//    @SerialName("forecastZone")
//    val forecastZone: String = "",
//    @SerialName("gridId")
//    val gridId: String = "",
//    @SerialName("gridX")
//    val gridX: Int = 0,
//    @SerialName("gridY")
//    val gridY: Int = 0,
//    @SerialName("@id")
//    val id: String = "",
//    @SerialName("observationStations")
//    val observationStations: String = "",
//    @SerialName("radarStation")
//    val radarStation: String = "",
//    @SerialName("relativeLocation")
//    val relativeLocation: RelativeLocation = RelativeLocation(),
//    @SerialName("timeZone")
//    val timeZone: String = "",
//    @SerialName("@type")
//    val type: String = ""
  ) {
//    @Serializable
//    data class RelativeLocation(
//      @SerialName("geometry")
//      val geometry: Geometry = Geometry(),
//      @SerialName("properties")
//      val properties: Properties = Properties(),
//      @SerialName("type")
//      val type: String = ""
//    ) {
//      @Serializable
//      data class Geometry(
//        @SerialName("coordinates")
//        val coordinates: List<Double> = listOf(),
//        @SerialName("type")
//        val type: String = ""
//      )
//
//      @Serializable
//      data class Properties(
//        @SerialName("bearing")
//        val bearing: Bearing = Bearing(),
//        @SerialName("city")
//        val city: String = "",
//        @SerialName("distance")
//        val distance: Distance = Distance(),
//        @SerialName("state")
//        val state: String = ""
//      ) {
//        @Serializable
//        data class Bearing(
//          @SerialName("unitCode")
//          val unitCode: String = "",
//          @SerialName("value")
//          val value: Int = 0
//        )
//
//        @Serializable
//        data class Distance(
//          @SerialName("unitCode")
//          val unitCode: String = "",
//          @SerialName("value")
//          val value: Double = 0.0
//        )
//      }
//    }
  }
}