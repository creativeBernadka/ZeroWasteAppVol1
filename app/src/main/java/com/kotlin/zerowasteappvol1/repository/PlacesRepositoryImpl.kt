package com.kotlin.zerowasteappvol1.repository

import android.arch.lifecycle.LiveData
import android.graphics.drawable.Drawable
import android.support.annotation.WorkerThread
import com.kotlin.zerowasteappvol1.UI.loadImageFromWebOperations
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.PlacesDao
import com.kotlin.zerowasteappvol1.database.ShortPlace
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(private val placesDao: PlacesDao):
    PlacesRepository {

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
        val calendar = Calendar.getInstance()
        val dayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> 1
            Calendar.TUESDAY -> 2
            Calendar.WEDNESDAY -> 3
            Calendar.THURSDAY -> 4
            Calendar.FRIDAY -> 5
            Calendar.SATURDAY -> 6
            Calendar.SUNDAY -> 7
            else -> 1
        }
        return placesDao.getMarkerDescription(name, coordinates.latitude, coordinates.longitude, dayOfWeek)
    }

    @WorkerThread
    override suspend fun getMarkerImagesAsync(shortPlace: ShortPlace?): List<Drawable?>{
        delay(500)
        val name = shortPlace!!.name
        val coordinates = shortPlace.coordinates
        val urls = placesDao.getMarkerImages(name, coordinates.latitude, coordinates.longitude)
        return urls.map { item ->
            loadImageFromWebOperations(item)
        }
    }

    @WorkerThread
    override suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>{
        delay(500)
        val name = shortPlace.name
        val coordinates = shortPlace.coordinates
        return placesDao.getMarkerDetails(name, coordinates.latitude, coordinates.longitude)
    }
}