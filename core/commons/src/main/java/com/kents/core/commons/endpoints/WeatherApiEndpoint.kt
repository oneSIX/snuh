package com.kents.core.commons.endpoints

object WeatherApiEndpoint {
    val baseUrl: String
        get() = "openlibrary.org"

    fun search(keyword: String, fields: String = "title,key", limit: Int = 10) =
        "/search.json?q=$keyword&fields=$fields&limit=$limit"

    fun work(id: String) =
        "/works/$id.json"
}

