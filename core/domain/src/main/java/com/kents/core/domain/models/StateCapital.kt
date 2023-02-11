package com.kents.core.domain.models

data class StateCapital(
    val state: String,
    val cityName: String,
    val geocode: String,
    val stationId: String
)

data class StateDisplayModel(
    val cityName: String,
    val state: String,
    val currentTemp: Double,
    val unitCode: String,
    val timeStamp: String
)

object StateData {
    val capitals: List<StateCapital> = listOf(
        StateCapital("Alabama", "Montgomery", "32.361538,-86.279118", "KMGM"),
        StateCapital("Alaska", "Juneau", "58.301935,-134.41974", "PAJN"),
        StateCapital("Arizona", "Phoenix", "33.448457,-112.073844", "KIWA"),
        StateCapital("Arkansas", "Little Rock", "34.736009,-92.331122", "KLIT"),
        StateCapital("California", "Sacramento", "38.555605,-121.468926", "KSAC"),
        StateCapital("Colorado", "Denver", "39.7391667,-104.984167", "KBKF"),
        StateCapital("Connecticut", "Hartford", "41.767,-72.677", "KHFD"),
        StateCapital("Delaware", "Dover", "39.161921,-75.526755", "KDOV"),
        StateCapital("Florida", "Tallahassee", "30.438256,-84.280733", "KTLH"),
        StateCapital("Georgia", "Atlanta", "33.76,-84.39", "KATL"),
        StateCapital("Hawaii", "Honolulu", "21.30895,-157.826182", "PHNL"),
        StateCapital("Idaho", "Boise", "43.613739,-116.237651", "KBOI"),
        StateCapital("Illinois", "Springfield", "39.783250,-89.650373", "KSPI"),
        StateCapital("Indiana", "Indianapolis", "39.790942,-86.147685", "KIND"),
        StateCapital("Iowa", "Des Moines", "41.590939,-93.620866", "KDSM"),
        StateCapital("Kansas", "Topeka", "39.04,-95.69", "KTOP"),
        StateCapital("Kentucky", "Frankfort", "38.197274,-84.86311", "KFFT"),
        StateCapital("Louisiana", "Baton Rouge", "30.45809,-91.140229", "KBTR"),
        StateCapital("Maine", "Augusta", "44.323535,-69.765261", "KAUG"),
        StateCapital("Maryland", "Annapolis", "38.972945,-76.501157", "KNAK"),
        StateCapital("Massachusetts", "Boston", "42.2352,-71.0275","KBOS"),
        StateCapital("Michigan", "Lansing", "42.7335,-84.5467", "KLAN"),
        StateCapital("Minnesota", "St. Paul", "44.95,-93.094", "KSTP"),
        StateCapital("Mississippi", "Jackson", "32.320,-90.207", "KJAN"),
        StateCapital("Missouri", "Jefferson City", "38.572954,-92.189283", "KJEF"),
        StateCapital("Montana", "Helena", "46.595805,-112.027031", "KHLN"),
        StateCapital("Nebraska", "Lincoln", "40.809868,-96.675345", "KLNK"),
        StateCapital("Nevada", "Carson City", "39.160949,-119.753877", "KCXP"),
        StateCapital("New Hampshire", "Concord", "43.220093,-71.549127", "KCON"),
        StateCapital("New Jersey", "Trenton", "40.221741,-74.756138", "KTTN"),
        StateCapital("New Mexico", "Santa Fe", "35.667231,-105.964575", "KSAF"),
        StateCapital("New York", "Albany", "42.659829,-73.781339", "KALB"),
        StateCapital("North Carolina", "Raleigh", "35.771,-78.638","KRDU"),
        StateCapital("North Dakota", "Bismarck", "48.813343,-100.779004", "KBIS"),
        StateCapital("Ohio", "Columbus", "39.962245,-83.000647", "KCMH"),
        StateCapital("Oklahoma", "Oklahoma City", "35.482309,-97.534994", "KOKC"),
        StateCapital("Oregon", "Salem", "44.931109,-123.029159", "KSLE"),
        StateCapital("Pennsylvania", "Harrisburg", "40.269789,-76.875613", "KMDT"),
        StateCapital("Rhode Island", "Providence", "41.82355,-71.422132", "KPVD"),
        StateCapital("South Carolina", "Columbia", "34.000,-81.035", "KCUB"),
        StateCapital("South Dakota", "Pierre", "44.367966,-100.336378", "KFSD"),
        StateCapital("Tennessee", "Nashville", "36.165,-86.784", "KBNA"),
        StateCapital("Texas", "Austin", "30.266667,-97.75", "KAUS"),
        StateCapital("Utah", "Salt Lake City", "40.7547,-111.892622", "KSLC"),
        StateCapital("Vermont", "Montpelier", "44.26639,-72.57194", "KMVL"),
        StateCapital("Virginia", "Richmond", "37.54,-77.46", "KRIC"),
        StateCapital("Washington", "Olympia", "47.042418,-122.893077", "KOLM"),
        StateCapital("West Virginia", "Charleston", "38.349497,-81.633294", "KCRW"),
        StateCapital("Wisconsin", "Madison", "43.074722,-89.384444", "KMSN"),
        StateCapital("Wyoming", "Cheyenne", "41.145548,-104.802042", "KCYS")
    )
}

