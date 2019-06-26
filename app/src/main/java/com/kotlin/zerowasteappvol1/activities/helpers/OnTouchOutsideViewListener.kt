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

        val shortPlaceJson = pref.getString("shortPlace", "")

        val gson = Gson()
        val place: ShortPlace = gson.fromJson(shortPlaceJson, ShortPlace::class.java)

        val markerIcon = getMarkerIcon(place.typeOfPlace)

        val markers = markerMap.filter {
                element ->
            element.value == place
        }
        val marker: Marker = markers.keys.first()
        marker.setIcon(activity.bitmapDescriptorFromVector(activity, markerIcon))

        activity.findViewById<LinearLayout>(R.id.linearLayout_carousel_images).removeAllViews()
        view.visibility = View.GONE
    }
}