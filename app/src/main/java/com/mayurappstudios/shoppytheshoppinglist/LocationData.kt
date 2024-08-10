package com.mayurappstudios.shoppytheshoppinglist

data class LocationData(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String? = null
)

data class GeocodingResponse(
    val results: List<GeoCodingResult>,
    val status: String
)

data class GeoCodingResult(
    val formatted_address: String
)