package com.kents.core.commons.endpoints

object WeatherApiEndpoint {
    val baseUrl: String
        get() = "api.weather.gov/"

//    fun observations(stationCodes: List<String>) = stationCodes.map { code ->
//        "/stations/$code/observations/latest"
//    }

    fun observation(stationCode: String) = "/stations/$stationCode/observations/latest"

//    fun forecast(stationCode: String, geoCodes: String) =
//        "/gridpoints/$stationCode/$geoCodes/forecast"

    fun search(keyword: String, fields: String = "title,key", limit: Int = 10) =
        "/search.json?q=$keyword&fields=$fields&limit=$limit"

    fun work(id: String) =
        "/works/$id.json"


}

