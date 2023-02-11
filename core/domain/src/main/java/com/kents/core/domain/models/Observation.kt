package com.kents.core.domain.models

import com.kents.core.data.models.observationDto.ObservationDto
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

// This is the display model for each Capital City within the initial list view on screen - just gonna get temp first.
data class Observation(
    val currentTemp: Double?,
    val unitCode: String,
    val timeStamp: String
)

fun ObservationDto.toModel(): Observation = Observation(
    currentTemp = parseCurrentTemp(
        this.properties.temperature.value,
        this.properties.temperature.unitCode
    ),
    unitCode = parseUnitCode(this.properties.temperature.unitCode),
    timeStamp = parseTimeStamp(this.properties.timestamp)
)

private fun parseUnitCode(input: String): String {
    return when (input) {
        "wmoUnit:degC" -> {
            "F" // For now ONLY using fahrenheit
        }

        "wmoUnit:degF" -> {
            "F"
        }

        else -> {
            ""
        }
    }
}

private fun parseCurrentTemp(input: Double?, unitCode: String): Double? {

    input?.let {
        return when (unitCode) {
            "wmoUnit:degC" -> {
                // turn it into F because we are in america
                //  °F = (°C × 9/5) + 32
                (input * (9 / 5)) + 32
            }

            "wmoUnit:degF" -> {
                input
            }

            else -> {
                null
            }
        }
    }
    return null
}

private fun parseTimeStamp(input: String): String {
    //2023-02-11T18:52:00+00:00
    val localDateTime = input.toInstant().toLocalDateTime(TimeZone.currentSystemDefault())
    val date = localDateTime.date.toString()
    val time = localDateTime.time.toString()

    return "$date +$time"
}
