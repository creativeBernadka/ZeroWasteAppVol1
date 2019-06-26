package com.kotlin.zerowasteappvol1.repository

import com.google.android.gms.maps.model.LatLng

data class ShortPlaceWithAddress(
    val name: String,
    val coordinates: LatLng,
    val address: String?,
    val typeOfPlace: String?
)