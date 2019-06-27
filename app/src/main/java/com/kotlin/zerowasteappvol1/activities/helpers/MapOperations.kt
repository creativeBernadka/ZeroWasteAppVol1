package com.kotlin.zerowasteappvol1.activities.helpers

import android.support.v7.widget.CardView
import android.view.View
import android.widget.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MapOperations(
    private val map: GoogleMap,
    private val activity: MapsActivity,
    private val viewModel: PlacesViewModel
):
    CoroutineScope,
    GoogleMap.OnMarkerClickListener
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var eventMarkerMap: HashMap<Marker, ShortPlace> = HashMap()

    fun addMarkersToMap(points: List<ShortPlace>){

        points.forEach{ point ->
            val markerIcon = getMarkerIcon(point.typeOfPlace)
            val marker = map.addMarker(
                MarkerOptions()
                    .position(LatLng(point.latitude, point.longitude))
                    .title(point.name)
                    .icon(bitmapDescriptorFromVector(activity, markerIcon))
            )
            eventMarkerMap[marker] = point
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        marker?.setIcon(BitmapDescriptorFactory.defaultMarker())
        val place: ShortPlace? = eventMarkerMap[marker]

        val pref = activity.getSharedPreferences("MyPreferences", 0)
        val editor = pref.edit()

        editor.remove("shortPlace")
        val shortPlaceJson = Gson().toJson(place)
        editor.putString("shortPlace", shortPlaceJson)
        editor.apply()


        activity.latLng = place?.coordinates
        activity.findViewById<RatingBar>(R.id.ratingBar).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_type_of_place).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_open_hours).visibility = View.GONE
        activity.findViewById<CardView>(R.id.cardView_carousel_images).visibility = View.GONE
        activity.findViewById<TextView>(R.id.textView_address).visibility = View.GONE
        activity.findViewById<Button>(R.id.button_call).visibility = View.GONE
        activity.findViewById<Button>(R.id.button_website).visibility = View.GONE
        activity.findViewById<LinearLayout>(R.id.linearLayout_short_description).visibility = View.VISIBLE
        activity.findViewById<ProgressBar>(R.id.progressBar_description).visibility = View.VISIBLE
        activity.findViewById<TextView>(R.id.textView_name).text = place?.name
        launch {
            viewModel.getPlaceDescription(place, activity)
        }
        return true //musi byc true, zeby nie pokazywalo infoWindow
    }
}