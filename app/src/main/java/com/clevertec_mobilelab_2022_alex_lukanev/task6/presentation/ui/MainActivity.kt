package com.clevertec_mobilelab_2022_alex_lukanev.task6.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.clevertec_mobilelab_2022_alex_lukanev.task6.R
import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.di.appComponent
import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppRepository
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.logic.Analytics
import com.clevertec_mobilelab_2022_alex_lukanev.task6.domain.model.BankPlace
import com.clevertec_mobilelab_2022_alex_lukanev.task6.presentation.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

private const val LOCATION_X = 52.425163
private const val LOCATION_Y = 31.015039
private const val CITY = "Гомель"
private const val NEAREST_BANK_PLACES = 10

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Inject
    fun trackOnStart(analytics: Analytics) {
        analytics.trackNewsRequest()
    }

    @Inject
    fun initViewModel(appRepository: AppRepository) {
        mainViewModel = MainViewModel(CITY, appRepository)
        mainViewModel.getLiveDataObserver().observe(
            this
        ) {
            if (it != null) {
                val mapFragment = supportFragmentManager.findFragmentById(
                    R.id.map
                ) as? SupportMapFragment
                mapFragment?.getMapAsync { googleMap ->
                    addMarkers(googleMap, it)
                }
            } else {
                Toast.makeText(this, "error in getting the date", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        mainViewModel.makeApicall()
    }

    private fun addMarkers(googleMap: GoogleMap, bankPlaces: MutableList<BankPlace>) {
        val gomelLatLng = LatLng(LOCATION_X, LOCATION_Y)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gomelLatLng, 15.0f))
        bankPlaces.sortBy { t -> t.distance }
        for (i in 0 until NEAREST_BANK_PLACES) {
            googleMap.addMarker(
                MarkerOptions()
                    .title(bankPlaces[i].type)
                    .snippet("${bankPlaces[i].addressType}${bankPlaces[i].address} ${bankPlaces[i].house}")
                    .position(LatLng(bankPlaces[i].gpsX, bankPlaces[i].gpsY))
            )
        }
    }
}
