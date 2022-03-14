package com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AtmInfoboxApiData(
    @Json(name = "address_type") val addressType: String,
    @Json(name = "address") val address: String,
    @Json(name = "house") val house: String,
    @Json(name = "gps_x") val gpsX: String,
    @Json(name = "gps_y") val gpsY: String
)

@JsonClass(generateAdapter = true)
data class FilialApiData(
    @Json(name = "street_type") val streetType: String,
    @Json(name = "street") val street: String,
    @Json(name = "home_number") val homeNumber: String,
    @Json(name = "GPS_X") val gpsX: String,
    @Json(name = "GPS_Y") val gpsY: String
)
