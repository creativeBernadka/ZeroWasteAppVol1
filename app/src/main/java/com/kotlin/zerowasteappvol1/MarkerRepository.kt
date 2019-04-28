package com.kotlin.zerowasteappvol1

import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.room.Place

interface MarkerRepository {

    suspend fun getAllDataAsync(): Array<Place>
    fun removeInstance(index: Int)
    fun addInstance(place: Place)
    fun findInCategory(category: String)
    fun findNearby(spot: LatLng)
}