package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.models.ShortPlace
import com.kotlin.zerowasteappvol1.models.PlaceDescription
import com.kotlin.zerowasteappvol1.repository.loadImageFromWebOperations
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import com.kotlin.zerowasteappvol1.models.ShortPlaceWithAddress
import javax.inject.Inject

class PlacesViewModelMock @Inject constructor(application: Application)
    : AndroidViewModel(application), PlacesViewModel {

    override var fiveNearestPlaces: MutableLiveData<List<ShortPlaceWithAddress>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var fiveBestFittingPlaces: MutableLiveData<List<ShortPlaceWithAddress?>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun getFiveNearestPlaces(location: LatLng, context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFiveBestFittingPlaces(name: String, context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override var allPlaces =  MutableLiveData<List<ShortPlace>>()
    override var placeDescription = MutableLiveData<PlaceDescription>()
    override var placeImages = MutableLiveData<List<Drawable?>>()

    init {

        allPlaces.postValue(listOf(ShortPlace(1, "Miejsce 1",  49.835543, 19.076082, "sklep"),
            ShortPlace(2, "Miejsce 2", 49.83455, 19.077633, "restauracja"),
            ShortPlace(3, "Miejsce 3",  49.834240, 19.079626, "punkt naprawczy")))

    }

    override fun getAllPlaces(){
        allPlaces.postValue(listOf(ShortPlace(1, "Miejsce 1",  49.835543, 19.076082, "sklep"),
            ShortPlace(2, "Miejsce 2", 49.83455, 19.077633, "restauracja"),
            ShortPlace(3, "Miejsce 3",  49.834240, 19.079626, "punkt naprawczy")))
    }

    override fun getPlaceDescription(shortPlace: ShortPlace?, context: Context){
        val place = PlaceDescription("Miejsce 1")
        place.rating = 5.0
        place.typeOfPlace = "sklep"
        place.phoneNumber = "123456789"
        place.website = "https://developer.android.com"
        place.startHour = "8.00"
        place.endHour = "16:00"
        place.address = "Ulica, numer, kod pocztowy, miasto"
        placeDescription.postValue(place)
        placeImages.postValue(listOf(loadImageFromWebOperations("https://bit.ly/2WbBDkJ")))
        }

    override fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}