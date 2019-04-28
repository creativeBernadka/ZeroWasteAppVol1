package com.kotlin.zerowasteappvol1

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.kotlin.zerowasteappvol1.room.Place
import com.kotlin.zerowasteappvol1.room.PlaceDescription
import com.kotlin.zerowasteappvol1.room.PlacesRoomDatabase
import com.kotlin.zerowasteappvol1.room.ShortPlace
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PlacesViewModel(application: Application): AndroidViewModel(application) {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob
    private val scope = CoroutineScope(coroutineContext)

    private val repository: PlacesRepository
    var allPlaces: LiveData<List<ShortPlace>>
    private lateinit var placeDescription: LiveData<PlaceDescription>

    init {
        val placesDao = PlacesRoomDatabase.getDatabase(application, scope).placesDao()
        repository = PlacesRepository(placesDao)
        allPlaces = repository.places
    }

//    fun getAllPlaces(): LiveData<List<ShortPlace>>{
//        scope.launch(Dispatchers.IO) {
//            allPlaces = repository.getAllPlacesAsync()
//        }
//        return allPlaces
//    }

    fun getPlaceDescription(shortPlace: ShortPlace?): LiveData<PlaceDescription>{
        scope.launch(Dispatchers.IO) {
            placeDescription = repository.getMarkerDescriptionAsync(shortPlace)
        }
        return placeDescription
    }

    fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>{
        TODO()
    }



}