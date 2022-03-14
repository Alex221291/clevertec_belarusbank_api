package com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network

import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.BankPlace

interface AppRepository {
    fun getBankPlaces(city: String): MutableList<BankPlace>?
}