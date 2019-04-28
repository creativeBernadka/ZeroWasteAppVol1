package com.kotlin.zerowasteappvol1

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.kotlin.zerowasteappvol1.room.Place
import com.kotlin.zerowasteappvol1.room.PlaceDescription
import com.kotlin.zerowasteappvol1.room.PlacesDao
import com.kotlin.zerowasteappvol1.room.ShortPlace
import kotlinx.coroutines.delay

class PlacesRepository(private val placesDao: PlacesDao) {

    val places: LiveData<List<ShortPlace>> = placesDao.getAllPlaces()
//    @WorkerThread
//    suspend fun getAllPlacesAsync(): LiveData<List<ShortPlace>>{
//        delay(2000)
//        return placesDao.getAllPlaces()
//    }

    @WorkerThread
    suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?): LiveData<PlaceDescription>{
        delay(500)
        val name = shortPlace!!.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerDescription(name, coordinates.latitude, coordinates.longitude)
    }

    @WorkerThread
    suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>{
        delay(500)
        val name = shortPlace.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerDetails(name, coordinates.latitude, coordinates.longitude)
    }
}