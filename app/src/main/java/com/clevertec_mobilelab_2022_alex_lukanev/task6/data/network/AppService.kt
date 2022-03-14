package com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network

import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.AtmInfoboxApiData
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.FilialApiData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET("/api/atm")
    fun getGomelAtms(@Query("city")city: String): Call<List<AtmInfoboxApiData>>?

    @GET("/api/infobox")
    fun getGomelInfoboxes(@Query("city")city: String): Call<List<AtmInfoboxApiData>>?

    @GET("/api/filials_info")
    fun getGomelFilials(@Query("city")city: String): Call<List<FilialApiData>>?
}