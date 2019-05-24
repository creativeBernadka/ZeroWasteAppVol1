package com.kotlin.zerowasteappvol1.repository

import android.arch.lifecycle.LiveData
import android.graphics.drawable.Drawable
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.ShortPlace

interface PlacesRepository {

    suspend fun getAllPlacesAsync(): List<ShortPlace>
    suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?): PlaceDescription?
    suspend fun getMarkerImagesAsync(shortPlace: ShortPlace?): List<Drawable?>
    suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>
}