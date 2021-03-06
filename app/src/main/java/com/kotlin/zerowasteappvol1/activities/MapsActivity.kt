package com.kotlin.zerowasteappvol1.activities

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.helpers.*
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.coroutines.CoroutineContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    lateinit var mMap: GoogleMap
    lateinit var points: List<ShortPlace>
    private val REQUEST_PERMISSION_CODE: Int = 123
    private var startClickTime = "0".toLong()
    private lateinit var mapOperator: MapOperations

    @Inject lateinit var placesViewModel: PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.title = "Zero Waste App"
        (application as ZeroWasteApplication).appComponent.inject(this)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
            .getMapAsync(this)

        progressBarMarkers.visibility = View.VISIBLE

        CreateListeners().createListenersForMapsActivity(this)
    }

    override fun onRestart() {
        super.onRestart()
        val pref = this.getSharedPreferences("MyPreferences", 0)

        val whichWasClicked = pref.getInt("whichOneWasClicked", 255)
        val editor = pref.edit()
        editor.remove("whichOneWasClicked")
        editor.apply()
        if (whichWasClicked != 255){
            val gson = Gson()
            val placeJson = when(whichWasClicked){
                1 -> pref.getString("firstPlace", "")
                2 -> pref.getString("secondPlace", "")
                3 -> pref.getString("thirdPlace", "")
                4 -> pref.getString("fourthPlace", "")
                5 -> pref.getString("fifthPlace", "")
                else -> ""
            }
            val place: ShortPlace = gson.fromJson(placeJson, ShortPlace::class.java)
            val markers = mapOperator.markerMap.filter {
                    element ->
                element.value == place
            }
            val marker: Marker = markers.keys.first()
            mapOperator.onMarkerClick(marker)
            with(mMap) {
                moveCamera(
                    CameraUpdateFactory
                        .newLatLngZoom(place.coordinates,15f)
                )
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapOperator = MapOperations(mMap, this, placesViewModel)
        mapOperator.setUpMap()

        CreateObservers().createObserversForMapsActivity(this, placesViewModel, mapOperator)

        if(::points.isInitialized){
            mapOperator.addMarkersToMap(points)
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN){
            startClickTime = Calendar.getInstance().timeInMillis;
        }
        if(ev.action == MotionEvent.ACTION_UP) {
            val clickDuration = Calendar.getInstance().timeInMillis - startClickTime
            if(clickDuration < 200) {
                val touchOutsideView = linearLayout_short_description
                if (touchOutsideView.visibility == View.VISIBLE) {
                    val viewRect = Rect()
                    touchOutsideView.getGlobalVisibleRect(viewRect)
                    if (!viewRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        mapOperator.onTouchOutside(touchOutsideView, ev)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mapOperator.setMapToCurrentLocation(mMap)
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
}