package com.kotlin.zerowasteappvol1.models

import com.google.android.gms.maps.model.LatLng

data class ShortPlace(
    val places_id: Int,
    val place_name: String,
    val latitude: Double,
    val longitude: Double,
    val type_of_place: String?
){
    val coordinates: LatLng
        get() = LatLng(latitude, longitude)
}