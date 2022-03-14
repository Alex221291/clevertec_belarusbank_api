package com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model

data class BankPlace(
    val type: String,
    val addressType: String,
    val address: String,
    val house: String,
    val gpsX: Double,
    val gpsY: Double,
    val distance: Double
)