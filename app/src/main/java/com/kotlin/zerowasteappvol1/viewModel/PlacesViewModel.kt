package com.kotlin.zerowasteappvol1.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.ShortPlace

interface PlacesViewModel {
    var allPlaces: MutableLiveData<List<ShortPlace>>
    var placeDescription: MutableLiveData<PlaceDescription>
    var placeImages: MutableLiveData<List<String>>

    fun getAllPlaces()
    fun getPlaceDescription(shortPlace: ShortPlace?)
    fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>
}