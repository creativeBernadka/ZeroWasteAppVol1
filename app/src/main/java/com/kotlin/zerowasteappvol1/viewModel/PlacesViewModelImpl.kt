package com.kotlin.zerowasteappvol1.presenter

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.PlacesRoomDatabase
import com.kotlin.zerowasteappvol1.database.ShortPlace
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PlacesViewModelImpl @Inject constructor(application: Application, var repository: PlacesRepository,
                                              var scope: CoroutineScope)
    : AndroidViewModel(application), PlacesViewModel {


    override var allPlaces =  MutableLiveData<List<ShortPlace>>()
    override var placeDescription = MutableLiveData<PlaceDescription>()
    override var placeImages = MutableLiveData<List<String>>()

    override fun getAllPlaces(){
        scope.launch(Dispatchers.IO) {
            allPlaces.postValue(async{repository.getAllPlacesAsync()}.await())
        }
    }

    override fun getPlaceDescription(shortPlace: ShortPlace?){
        scope.launch(Dispatchers.IO) {
            placeDescription.postValue(async{repository.getMarkerDescriptionAsync(shortPlace)}.await())
            placeImages.postValue(async { repository.getMarkerImagesAsync(shortPlace) }.await())
        }
    }

    override fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>{
        TODO()
    }



}