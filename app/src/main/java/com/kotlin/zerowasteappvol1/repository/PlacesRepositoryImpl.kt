package com.kotlin.zerowasteappvol1.presenter

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.PlacesDao
import com.kotlin.zerowasteappvol1.database.ShortPlace
import kotlinx.coroutines.delay
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(private val placesDao: PlacesDao): PlacesRepository {

//    val places: LiveData<List<ShortPlace>> = placesDao.getAllPlaces()
    @WorkerThread
    override suspend fun getAllPlacesAsync(): List<ShortPlace>{
        delay(2000)
        return placesDao.getAllPlaces()
    }

    @WorkerThread
    override suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?): PlaceDescription?{
        delay(500)
        val name = shortPlace!!.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerDescription(name, coordinates.latitude, coordinates.longitude)
    }

    @WorkerThread
    override suspend fun getMarkerImagesAsync(shortPlace: ShortPlace?): List<String>{
        delay(500)
        val name = shortPlace!!.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerImages(name, coordinates.latitude, coordinates.longitude)
    }

    @WorkerThread
    override suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>{
        delay(500)
        val name = shortPlace.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerDetails(name, coordinates.latitude, coordinates.longitude)
    }
}