package com.kotlin.zerowasteappvol1.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.ShortPlace
import com.kotlin.zerowasteappvol1.repository.PlaceDescriptionWithAddress
import com.kotlin.zerowasteappvol1.repository.PlacesRepository
import kotlinx.coroutines.*
import me.xdrop.fuzzywuzzy.FuzzySearch
import me.xdrop.fuzzywuzzy.model.ExtractedResult
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class PlacesViewModelImpl @Inject constructor(application: Application, var repository: PlacesRepository,
                                              var scope: CoroutineScope)
    : AndroidViewModel(application), PlacesViewModel {

    override var allPlaces =  MutableLiveData<List<ShortPlace>>()
    override var placeDescription = MutableLiveData<PlaceDescriptionWithAddress>()
    override var placeImages = MutableLiveData<List<Drawable?>>()
    override var fiveNearestPlaces =  MutableLiveData<List<ShortPlace>>()
    override var fiveBestFittingPlaces = MutableLiveData<List<ShortPlace?>>()

    init {
        scope.launch(Dispatchers.IO) {
            allPlaces.postValue(async{repository.getAllPlacesAsync()}.await())
        }
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

    override fun getFiveBestFittingPlaces(name: String) {
        val places = allPlaces.value
        val namesMarkerMap: HashMap<String, ShortPlace> = HashMap()
        val addressMarkerMap: HashMap<String, ShortPlace> = HashMap()
        val geocoder = Geocoder(getApplication(), Locale.getDefault())

        val placesNames = places?.map { place ->
            namesMarkerMap[place.name] = place
            place.name
        }

        val bestFiveWithRespectToNameWithResults = FuzzySearch.extractTop(name, placesNames, 5)

        val placesAddresses = places?.map { place ->
            val addresses: List<Address>? =
                try{
                    geocoder.getFromLocation(place.latitude, place.longitude, 1)
                }
                catch(e:Exception){
                    null
                }
            val address = addresses!!.map{item -> item.getAddressLine(0)?.toString()}[0]

            if (address != null){
                addressMarkerMap[address] = place
            }
            address
        }

        val bestFiveWithRespectToAddressWithResults = FuzzySearch.extractTop(name, placesAddresses, 5)

        val bestFiveWithResults = bestFiveWithRespectToAddressWithResults.union(bestFiveWithRespectToNameWithResults)

        val bestFiveSorted = bestFiveWithResults.sortedWith(CompareObjects)

        val bestFive: List<ShortPlace?> = bestFiveSorted.map { place ->
            if( place.string in namesMarkerMap.keys){
                return@map namesMarkerMap[place.string]
            }
            else{
                return@map addressMarkerMap[place.string]
            }
        }
        
        fiveBestFittingPlaces.postValue(bestFive)
    }


    override fun getFiveNearestPlaces(location: LatLng) {
        val places = allPlaces.value
        val distanceMarkerMap: HashMap<Float, ShortPlace> = HashMap()
        val startLocation = Location("start location")
        startLocation.latitude = location.latitude
        startLocation.longitude = location.longitude

        places?.forEach{ place ->
            val endLocation = Location("end location")
            endLocation.latitude = place.latitude
            endLocation.longitude = place.longitude
            distanceMarkerMap[startLocation.distanceTo(endLocation)] = place
        }

        val sortedPlaces = distanceMarkerMap.toSortedMap().values

        fiveNearestPlaces.postValue(sortedPlaces.take(5))
    }


}

class CompareObjects {

    companion object : Comparator<ExtractedResult> {

        override fun compare(a: ExtractedResult, b: ExtractedResult): Int = b.score - a.score

    }
}