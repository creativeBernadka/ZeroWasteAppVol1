//package com.kotlin.zerowasteappvol1.dagger
//
//import android.app.Application
//import android.arch.lifecycle.AndroidViewModel
//import android.arch.lifecycle.LiveData
//import android.arch.lifecycle.MutableLiveData
//import com.kotlin.zerowasteappvol1.database.Place
//import com.kotlin.zerowasteappvol1.database.PlaceDescription
//import com.kotlin.zerowasteappvol1.database.ShortPlace
//import com.kotlin.zerowasteappvol1.presenter.PlacesViewModel
//import javax.inject.Inject
//
//class PlacesViewModelMock @Inject constructor(application: Application)
//    : AndroidViewModel(application), PlacesViewModel {
//
//    override var allPlaces =  MutableLiveData<List<ShortPlace>>()
//    override var placeDescription = MutableLiveData<PlaceDescription>()
//    override var placeImages = MutableLiveData<List<String>>()
//
//    override fun getAllPlaces(){
//        allPlaces.postValue(listOf(ShortPlace("Miejsce 1",  49.835543, 19.076082),
//            ShortPlace("Miejsce 2", 49.83455, 19.077633),
//            ShortPlace("Miejsce 3",  49.834240, 19.079626)))
//    }
//
//    override fun getPlaceDescription(shortPlace: ShortPlace?){
//        placeDescription.postValue(
//            PlaceDescription("Miejsce 1",  49.835543, 19.076082, 5.0,
//                    "sklep", 8.00,16.00 ))
//        placeImages.postValue(listOf("https://bit.ly/2WbBDkJ"))
//        }
//
//    override fun getPlaceDetails(shortPlace: ShortPlace): LiveData<Place> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}