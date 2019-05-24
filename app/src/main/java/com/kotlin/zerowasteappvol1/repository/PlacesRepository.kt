package com.kotlin.zerowasteappvol1.presenter

import android.arch.lifecycle.LiveData
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.ShortPlace

interface PlacesRepository {

    suspend fun getAllPlacesAsync(): List<ShortPlace>
    suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?): PlaceDescription?
    suspend fun getMarkerImagesAsync(shortPlace: ShortPlace?): List<String>
    suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>
}