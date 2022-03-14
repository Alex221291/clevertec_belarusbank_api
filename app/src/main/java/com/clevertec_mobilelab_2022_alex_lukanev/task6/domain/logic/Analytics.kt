package com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.logic

import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppService
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.AtmInfoboxApiData
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.BankPlace
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.FilialApiData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

private const val LOCATION_X = 52.425163
private const val LOCATION_Y = 31.015039
private const val ATM = "банкомат"
private const val INFOBOX = "инфокиоск"
private const val FILIAL = "отделение"

class Analytics @Inject constructor(private val appService: AppService) {
    fun getBankPlaces(city: String): MutableList<BankPlace>? {

        var bankPlaces: MutableList<BankPlace>? = mutableListOf()
        val atmsCall: Call<List<AtmInfoboxApiData>>? = appService.getGomelAtms(city)
        val infoboxesCall: Call<List<AtmInfoboxApiData>>? = appService.getGomelInfoboxes(city)
        val filialsCall: Call<List<FilialApiData>>? = appService.getGomelFilials(city)

        atmsCall?.enqueue(object : Callback<List<AtmInfoboxApiData>> {
            override fun onFailure(call: Call<List<AtmInfoboxApiData>>, t: Throwable) {
                bankPlaces = null
            }

            override fun onResponse(
                call: Call<List<AtmInfoboxApiData>>,
                response: Response<List<AtmInfoboxApiData>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        bankPlaces?.add(
                            BankPlace(
                                ATM,
                                it.addressType,
                                it.address,
                                it.house,
                                it.gpsX.toDouble(),
                                it.gpsY.toDouble(),
                                distance(it.gpsX.toDouble(), it.gpsY.toDouble())
                            )
                        )
                    }
                } else {
                    bankPlaces = null
                }
            }
        })
        infoboxesCall?.enqueue(object : Callback<List<AtmInfoboxApiData>> {
            override fun onFailure(call: Call<List<AtmInfoboxApiData>>, t: Throwable) {
                bankPlaces = null
            }

            override fun onResponse(
                call: Call<List<AtmInfoboxApiData>>,
                response: Response<List<AtmInfoboxApiData>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        bankPlaces?.add(
                            BankPlace(
                                INFOBOX,
                                it.addressType,
                                it.address,
                                it.house,
                                it.gpsX.toDouble(),
                                it.gpsY.toDouble(),
                                distance(it.gpsX.toDouble(), it.gpsY.toDouble())
                            )
                        )
                    }
                } else {
                    bankPlaces = null
                }
            }
        })
        filialsCall?.enqueue(object : Callback<List<FilialApiData>> {
            override fun onFailure(call: Call<List<FilialApiData>>, t: Throwable) {
                bankPlaces = null
            }

            override fun onResponse(
                call: Call<List<FilialApiData>>,
                response: Response<List<FilialApiData>>
            ) {
                if (response.isSuccessful) {
                    val items = response.body()
                    items?.forEach {
                        bankPlaces?.add(
                            BankPlace(
                                FILIAL,
                                it.streetType,
                                it.street,
                                it.homeNumber,
                                it.gpsX.toDouble(),
                                it.gpsY.toDouble(),
                                distance(it.gpsX.toDouble(), it.gpsY.toDouble())
                            )
                        )
                    }
                } else {
                    bankPlaces = null
                }
            }
        })

        return bankPlaces
    }

    fun trackNewsRequest() {
    }

    private fun distance(gpsX: Double, gpsY: Double): Double {
        return sqrt((gpsX - LOCATION_X).pow(2.0) + (gpsY - LOCATION_Y).pow(2.0))
    }
}