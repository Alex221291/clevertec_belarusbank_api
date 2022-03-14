package com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network

import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.logic.Analytics
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.BankPlace
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val analytics: Analytics
) : AppRepository {
    override fun getBankPlaces(city: String): MutableList<BankPlace>? {
        return analytics.getBankPlaces(city)
    }
}