package com.kotlin.zerowasteappvol1.models

import com.google.android.gms.maps.model.LatLng

data class ShortPlaceWithAddress(
    val id: Int,
    val name: String,
    val coordinates: LatLng,
    val address: String?,
    val typeOfPlace: String?
)