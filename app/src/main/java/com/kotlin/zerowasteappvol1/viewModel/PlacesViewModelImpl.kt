package com.kotlin.zerowasteappvol1.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.models.ShortPlace
import com.kotlin.zerowasteappvol1.repository.PlaceDescriptionWithAddress
import com.kotlin.zerowasteappvol1.repository.PlacesRepository
import com.kotlin.zerowasteappvol1.repository.ShortPlaceWithAddress
import kotlinx.coroutines.*
import javax.inject.Inject

class PlacesViewModelImpl @Inject constructor(application: Application, var repository: PlacesRepository,
                                              var scope: CoroutineScope)
    : AndroidViewModel(application), PlacesViewModel {

    override var allPlaces =  MutableLiveData<List<ShortPlace>>()
    override var placeDescription = MutableLiveData<PlaceDescriptionWithAddress>()
    override var placeImages = MutableLiveData<List<Drawable?>>()
    override var fiveNearestPlaces =  MutableLiveData<List<ShortPlaceWithAddress>>()
    override var fiveBestFittingPlaces = MutableLiveData<List<ShortPlaceWithAddress?>>()

    init {
        getAllPlaces()
    }

    override fun getAllPlaces(){
        scope.launch(Dispatchers.IO) {
            allPlaces.postValue(async{repository.getAllPlacesAsync()}.await())
        }
    }

    override fun getPlaceDescription(shortPlace: ShortPlace?, context: Context){
        scope.launch(Dispatchers.IO) {
            placeDescription.postValue(async{repository.getMarkerDescriptionAsync(shortPlace, context)}.await())
            placeImages.postValue(async { repository.getMarkerImagesAsync(shortPlace) }.await())
        }
    }

    override fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place>{
        TODO()
    }

    override fun getFiveBestFittingPlaces(name: String, context: Context) {
        scope.launch(Dispatchers.IO) {
            fiveBestFittingPlaces.postValue(async { repository.getFiveBestFittingPlacesAsync(name, context) }.await())
        }
    }

    override fun getFiveNearestPlaces(location: LatLng, context: Context) {
        scope.launch(Dispatchers.IO) {
            fiveNearestPlaces.postValue(async { repository.getFiveNearestPlacesAsync(location, context) }.await())
        }
    }
}