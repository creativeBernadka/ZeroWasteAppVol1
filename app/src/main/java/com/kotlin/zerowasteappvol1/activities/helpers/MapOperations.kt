package com.kotlin.zerowasteappvol1.activities.helpers

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.kotlin.zerowasteappvol1.activities.MapsActivity
import com.kotlin.zerowasteappvol1.database.ShortPlace

class MapOperations(private val map: GoogleMap){

    private var eventMarkerMap: HashMap<Marker, ShortPlace> = HashMap()

    fun addMarkersToMap(activity: MapsActivity, points: List<ShortPlace>){

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
}