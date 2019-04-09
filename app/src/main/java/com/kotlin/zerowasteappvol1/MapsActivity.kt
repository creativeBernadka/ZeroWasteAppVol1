package com.kotlin.zerowasteappvol1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
//import sun.awt.windows.ThemeReader.getPosition
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.marker_popup.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val REQUEST_PERMISSION_CODE: Int = 123
    private val place = Places("Miejsce 1", LatLng(49.83455, 19.077633), "Orchidei 22H",
        "123456789")
    @Inject lateinit var markerRepository: MarkerRepository
//    private val place = Places("Miejsce 1", LatLng( 49.785538, 19.057291), "Orchidei 22H",
//    "123456789")
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_maps)
    supportActionBar?.setTitle("Zero Waste App")
    DaggerMapComponent.create().inject(this)
    val mapFragment = supportFragmentManager
        .findFragmentById(R.id.map) as SupportMapFragment
    mapFragment.getMapAsync(this)


    SearchPanel.setOnEditorActionListener { _, actionId, _ ->
        //            do czyszczenia SearchPanelu
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> {
                SearchPanel.text.clear()
                true
            }
            else -> false
        }
    }

    ShowPlacesButton.setOnClickListener {
        val window = PopupWindow(this)
        val view = layoutInflater.inflate(R.layout.loading_popup, null)
        window.contentView = view
        window.showAtLocation(ShowPlacesButton, Gravity.CENTER, 0, 0)
        GlobalScope.launch {
            val points = markerRepository.getAllData()
            for (point in points) {
//                    mMap.addMarker(MarkerOptions().position(point.Coordinates).title(point.Name))
            }
            delay(2000)
            window.dismiss()
        }
//        }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setPadding(0, 70, 0, 0)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_CODE)
        }
        else{
            setMapToCurrentLocation(mMap)
        }
        mMap.addMarker(MarkerOptions().position(place.Coordinates).title(place.Name))

        mMap.setOnMarkerClickListener{
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.marker_popup,null)
            window.contentView = view
            window.showAtLocation(SearchPanel, Gravity.BOTTOM, 0, 0)
            val placeNameText = view.findViewById<TextView>(R.id.textName)
            val placeAddressText = view.findViewById<TextView>(R.id.textAddress)
            val placePhoneText = view.findViewById<TextView>(R.id.textPhone)
            placeNameText.text = place.Name
            placeAddressText.text = place.Address
            placePhoneText.text = place.PhoneNumber
            val closeButtonHandler = view.findViewById<Button>(R.id.closeButton)
            closeButtonHandler.setOnClickListener {
                window.dismiss()
            }
            true
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    setMapToCurrentLocation(mMap)
                }
                else{
                    val defaultLocation = LatLng(52.114339, 19.423672)
                    with(mMap){
                        moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 5f))
                    }
                }
            }
        }
    }

    private fun setMapToCurrentLocation(googleMap: GoogleMap){
//        Zastanowic sie, czy nie zrobic lokalizacji na GPS lub jakiegos 'wybierz najlepszy lokalizator'
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider: String = LocationManager.NETWORK_PROVIDER
        val lastKnownLocation: Location = locationManager.getLastKnownLocation(locationProvider)
        val currentLocation = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
        with(googleMap){
            moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
            googleMap.setMyLocationEnabled(true)
        }
    }
}