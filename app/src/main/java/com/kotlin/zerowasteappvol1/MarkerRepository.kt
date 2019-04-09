package com.kotlin.zerowasteappvol1

import com.google.android.gms.maps.model.LatLng

interface MarkerRepository {

    fun getAllData(): Array<Places>
    fun removeInstance(index: Int)
    fun addInstance(place: Places)
    fun findInCategory(category: String)
    fun findNearby(spot: LatLng)
}