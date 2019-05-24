package com.kotlin.zerowasteappvol1.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.drawable.Drawable
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.ShortPlace

interface PlacesViewModel {
    var allPlaces: MutableLiveData<List<ShortPlace>>
    var placeDescription: MutableLiveData<PlaceDescription>
    var placeImages: MutableLiveData<List<Drawable?>>

    fun getAllPlaces()
    fun getPlaceDescription(shortPlace: ShortPlace?)
    fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>
}