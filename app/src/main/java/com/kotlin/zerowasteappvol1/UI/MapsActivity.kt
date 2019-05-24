package com.kotlin.zerowasteappvol1.UI

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private lateinit var mMap: GoogleMap
    private val REQUEST_PERMISSION_CODE: Int = 123
    private val INITIAL_ITEMS_COUNT = 3.5f
    private lateinit var points: List<ShortPlace>
    private var eventMarkerMap: HashMap<Marker, ShortPlace> = HashMap()
    private lateinit var mTouchOutsideView: View
    private lateinit var onTouchOutsideViewListener: OnTouchOutsideViewListener

    @Inject lateinit var placesViewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.title = "Zero Waste App"
        (application as ZeroWasteApplication).appComponent.inject(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        onTouchOutsideViewListener = OnTouchOutsideViewListener()
        setOnTouchOutsideViewListener(linearLayout_short_description, onTouchOutsideViewListener)

        progressBarMarkers.visibility = View.VISIBLE

        placesViewModel.allPlaces.observe(this, Observer { places ->
            if(places != null){
                points = places
                if (::mMap.isInitialized){
                    addMarkersToMap()
                    progressBarMarkers.visibility = View.GONE
                }
            }
        })

        placesViewModel.placeDescription.observe(this, Observer { place ->
            if(place != null) {
                ratingBar.rating = place.rating!!.toFloat()
                textView_type_of_place.text = place.typeOfPlace
                textView_open_hours.text = place.startHour + " - " + place.endHour
                progressBar_description.visibility = View.GONE
                ratingBar.visibility = View.VISIBLE
                textView_type_of_place.visibility = View.VISIBLE
                textView_open_hours.visibility = View.VISIBLE
            }
        })

        placesViewModel.placeImages.observe(this, Observer { images ->
            if(images != null){
                displayImages(images)
            }
        })

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
        mMap.setOnMarkerClickListener(OnMarkerClickListener(eventMarkerMap, placesViewModel, this))

        if(::points.isInitialized){
            addMarkersToMap()
        }

    }

    private fun addMarkersToMap(){
        for (point in points){
            val marker = mMap.addMarker(MarkerOptions().position(LatLng(point.latitude, point.longitude)).title(point.name))
            eventMarkerMap[marker] = point
        }
    }

    private fun setOnTouchOutsideViewListener(view: View, onTouchOutsideViewListener: OnTouchOutsideViewListener) {
        mTouchOutsideView = view
        this.onTouchOutsideViewListener = onTouchOutsideViewListener
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            // Notify touch outside listener if user tapped outside a given view
            if (mTouchOutsideView.visibility == View.VISIBLE) {
                val viewRect = Rect()
                mTouchOutsideView.getGlobalVisibleRect(viewRect)
                if (!viewRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    onTouchOutsideViewListener.onTouchOutside(mTouchOutsideView, ev)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun displayImages(imagesList: List<Drawable?>){
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val imageWidth = (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT).toInt()
        var imageItem: ImageView
        var countNulls = 0
        linearLayout_carousel_images.removeAllViews()
        imagesList.map {item ->
            imageItem = ImageView(this)
            if(item == null) countNulls += 1
            else{
                imageItem.setImageDrawable(item)
                imageItem.layoutParams = LinearLayout.LayoutParams(imageWidth, imageWidth)
                linearLayout_carousel_images.addView(imageItem)
            }
        }
        if (countNulls != imagesList.size) cardView_carousel_images.visibility = View.VISIBLE
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
        var currentLocation: LatLng
        currentLocation = if (locationManager.getLastKnownLocation(locationProvider) == null){
            LatLng(49.835543, 19.076082)
        } else{
            val lastKnownLocation: Location = locationManager.getLastKnownLocation(locationProvider)
            LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
        }

//        var currentLocation = LatLng(49.835543, 19.076082)
        with(googleMap){
            moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
            googleMap.isMyLocationEnabled = true
        }
    }
}