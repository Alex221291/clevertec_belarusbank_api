package com.clevertec_mobilelab_2022_alex_lukanev.task6.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppRepository
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.BankPlace

class MainViewModel(
    private val city: String,
    private val appRepository: AppRepository
) : ViewModel() {

    private var liveDataList: MutableLiveData<MutableList<BankPlace>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<MutableList<BankPlace>> {
        return liveDataList
    }

    fun makeApicall() {
        return liveDataList.postValue(appRepository.getBankPlaces(city))
    }
}