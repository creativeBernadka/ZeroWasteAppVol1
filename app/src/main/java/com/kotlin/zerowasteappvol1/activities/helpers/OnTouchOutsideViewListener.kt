package com.kotlin.zerowasteappvol1.activities.helpers

import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.google.android.gms.maps.model.Marker
import com.kotlin.zerowasteappvol1.R
import com.google.gson.Gson
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.database.ShortPlace



class OnTouchOutsideViewListener{
    fun onTouchOutside(view: View, event: MotionEvent, activity: MapsActivity, markerMap: HashMap<Marker, ShortPlace>) {

        val pref = activity.getSharedPreferences("MyPreferences", 0)

        val markerJson = pref.getString("marker", "")
        val shortPlaceJson = pref.getString("shortPlace", "")

        val gson = Gson()
        val place: ShortPlace = gson.fromJson(shortPlaceJson, ShortPlace::class.java)

        val markerIcon = when (place.typeOfPlace){
            "sklep" -> R.drawable.ic_marker_icon_yellow
            "restauracja" -> R.drawable.ic_marker_icon_green
            "punkt naprawczy" -> R.drawable.ic_marker_icon_blue
            else -> R.drawable.ic_marker_icon_plain
        }

        val markers = markerMap.filter { element -> element.value == place }
        val marker: Marker = markers.keys.first()
        marker.setIcon(activity.bitmapDescriptorFromVector(activity, markerIcon))

        activity.findViewById<LinearLayout>(R.id.linearLayout_carousel_images).removeAllViews()
        view.visibility = View.GONE
    }
}