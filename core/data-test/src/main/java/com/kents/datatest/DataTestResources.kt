package com.kents.datatest

object DataTestResources {
    fun weatherPointsJson(): String =
        loadJsonResource("points")

    private fun loadJsonResource(fileName: String) =
        javaClass.classLoader!!
            .getResource("$fileName.json")!!
            .readText()
}
