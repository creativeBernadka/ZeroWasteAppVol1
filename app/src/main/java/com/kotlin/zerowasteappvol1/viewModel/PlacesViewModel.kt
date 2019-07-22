package com.kotlin.zerowasteappvol1.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.models.ShortPlace
import com.kotlin.zerowasteappvol1.models.PlaceDescription
import com.kotlin.zerowasteappvol1.models.ShortPlaceWithAddress

interface PlacesViewModel {
    var allPlaces: MutableLiveData<List<ShortPlace>>
    var placeDescription: MutableLiveData<PlaceDescription>
    var placeImages: MutableLiveData<List<Drawable?>>
    var fiveNearestPlaces: MutableLiveData<List<ShortPlaceWithAddress>>
    var fiveBestFittingPlaces: MutableLiveData<List<ShortPlaceWithAddress?>>

    fun getAllPlaces()
    fun getPlaceDescription(shortPlace: ShortPlace?, context: Context)
    fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>
    fun getFiveNearestPlaces(location: LatLng, context: Context)
    fun getFiveBestFittingPlaces(name: String, context: Context)
}