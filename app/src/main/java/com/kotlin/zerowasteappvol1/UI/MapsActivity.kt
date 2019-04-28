package com.kotlin.zerowasteappvol1.UI

//import sun.awt.windows.ThemeReader.getPosition
import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Rect
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.zerowasteappvol1.OnTouchOutsideViewListener
import com.kotlin.zerowasteappvol1.OnTouchOutsideViewListenerImpl
import com.kotlin.zerowasteappvol1.PlacesViewModel
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.room.ShortPlace
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope, GoogleMap.OnMarkerClickListener{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    private lateinit var mMap: GoogleMap
    private val REQUEST_PERMISSION_CODE: Int = 123
//    @Inject lateinit var markerRepository: MarkerRepository
    private lateinit var points: List<ShortPlace>
    private var eventMarkerMap: HashMap<Marker, ShortPlace> = HashMap()
    private lateinit var placesViewModel: PlacesViewModel //DI
    private lateinit var mTouchOutsideView: View
    private lateinit var onTouchOutsideViewListener: OnTouchOutsideViewListener //DI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.title = "Zero Waste App"
//        DaggerAppComponent.create().inject(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        placesViewModel = ViewModelProviders.of(this).get(PlacesViewModel::class.java)
        onTouchOutsideViewListener = OnTouchOutsideViewListenerImpl()

        placesViewModel.allPlaces.observe(this, Observer { places ->
//            progressBarMarkers.visibility = View.VISIBLE
            if(places != null){
                points = places
                addMarkersToMap()
            }
//            progressBarMarkers.visibility = View.GONE
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

        setOnTouchOutsideViewListener(layout_short_description, onTouchOutsideViewListener)

//            launch{
//                progressBarMarkers.visibility = View.VISIBLE
//                try {
//                    points = async { markerRepository.getAllDataAsync() }.await()
//                    addMarkersToMap()
//                }catch (ex:Exception){
//
//                }finally {
//                    progressBarMarkers.visibility = View.INVISIBLE
//                }
//            }
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
        mMap.setOnMarkerClickListener(this)

    }

    private fun addMarkersToMap(){
        for (point in points){
            val marker = mMap.addMarker(MarkerOptions().position(point.coordinates).title(point.name))
            eventMarkerMap[marker] = point
        }
    }

    override fun onMarkerClick(marker: Marker):Boolean {
        val place: ShortPlace? = eventMarkerMap[marker]
        layout_short_description.visibility = View.VISIBLE
        textView_name.text = place?.name
        return true //musi byc true, zeby nie pokazywalo infoWindow
    }

    fun setOnTouchOutsideViewListener(view: View, onTouchOutsideViewListener: OnTouchOutsideViewListener) {
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
        val locationProvider: String = LocationManager.GPS_PROVIDER
        val lastKnownLocation: Location = locationManager.getLastKnownLocation(locationProvider)
        val currentLocation = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
        with(googleMap){
            moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
            googleMap.isMyLocationEnabled = true
        }
    }
}