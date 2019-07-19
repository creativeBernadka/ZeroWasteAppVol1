package com.kotlin.zerowasteappvol1.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.models.*

interface PlacesRepository {

    suspend fun getAllPlacesAsync(): List<ShortPlace>
    suspend fun getMarkerDescriptionAsync(shortPlaceID: Int, context: Context): DescriptionX
    suspend fun getMarkerImagesAsync(urls: List<Image>): List<Drawable?>
    suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>
    suspend fun getFiveNearestPlacesAsync(location: LatLng, context: Context): List<ShortPlaceWithAddress>
    suspend fun getFiveBestFittingPlacesAsync(name: String, context: Context): List<ShortPlaceWithAddress?>
}