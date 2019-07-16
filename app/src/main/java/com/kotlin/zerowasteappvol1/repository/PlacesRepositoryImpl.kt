package com.kotlin.zerowasteappvol1.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.graphics.drawable.Drawable
import android.location.Address
import android.support.annotation.WorkerThread
import com.kotlin.zerowasteappvol1.database.Place
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.models.ShortPlace
import com.kotlin.zerowasteappvol1.repository.retrofit.RetrofitService
import me.xdrop.fuzzywuzzy.FuzzySearch
import me.xdrop.fuzzywuzzy.model.ExtractedResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlacesRepositoryImpl:
    PlacesRepository {

    lateinit var allPlaces: List<ShortPlace>

    object RetrofitFactory {
        private const val ip = "192.168.1.198"
        private const val BASE_URL = "http://$ip:3000/"

        fun makeRetrofitService(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }

    @WorkerThread
    override suspend fun getAllPlacesAsync(): List<ShortPlace>{
//        delay(2000)
        val service = RetrofitFactory.makeRetrofitService()
        val places = service.getAllPlaces()
        allPlaces = places.places
        return allPlaces
    }

    @WorkerThread
    override suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?, context: Context)
            : PlaceDescriptionWithAddress?{
//        delay(500)
//
//        val name = shortPlace!!.place_name
//        val coordinates = shortPlace.coordinates
//        val calendar = Calendar.getInstance()
//        val dayOfWeek =
//            when (calendar.get(Calendar.DAY_OF_WEEK)){
//                Calendar.MONDAY -> 1
//                Calendar.TUESDAY -> 2
//                Calendar.WEDNESDAY -> 3
//                Calendar.THURSDAY -> 4
//                Calendar.FRIDAY -> 5
//                Calendar.SATURDAY -> 6
//                Calendar.SUNDAY -> 7
//                else -> 1
//            }
//
//        val markerDescription = placesDao.getMarkerDescription(name, coordinates.latitude, coordinates.longitude, dayOfWeek)
//        val placeDescriptionWithAddress = PlaceDescriptionWithAddress(name)
//
//        placeDescriptionWithAddress.rating = markerDescription.rating
//        placeDescriptionWithAddress.typeOfPlace = markerDescription.typeOfPlace
//        placeDescriptionWithAddress.startHour = markerDescription.startHour
//        placeDescriptionWithAddress.endHour = markerDescription.endHour
//        placeDescriptionWithAddress.phoneNumber = markerDescription.phoneNumber
//        placeDescriptionWithAddress.website = markerDescription.website
//
//        val geocoder = Geocoder(context, Locale.getDefault())
//        val addresses: List<Address>? =
//            try{
//                geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)
//            }
//            catch(e:Exception){
//                null
//            }
//
//        placeDescriptionWithAddress.address =
//            if (addresses != null){
//                addresses.map{item -> item.getAddressLine(0)?.toString()}[0]
//            }
//            else{
//                null
//            }
//
//        return placeDescriptionWithAddress
        TODO()
    }

    @WorkerThread
    override suspend fun getMarkerImagesAsync(shortPlace: ShortPlace?): List<Drawable?>{
//        delay(500)
//        val name = shortPlace!!.place_name
//        val coordinates = shortPlace.coordinates
//        val urls = placesDao.getMarkerImages(name, coordinates.latitude, coordinates.longitude)
//        return urls.map { item ->
//            loadImageFromWebOperations(item)
//        }
        TODO()
    }

    @WorkerThread
    override suspend fun getMarkerDetailsAsync(shortPlace: ShortPlace): LiveData<Place>{
//        delay(500)
//        val name = shortPlace.place_name
//        val coordinates = shortPlace.coordinates
//        return placesDao.getMarkerDetails(name, coordinates.latitude, coordinates.longitude)
        TODO()
    }

    @WorkerThread
    override suspend fun getFiveNearestPlacesAsync(location: LatLng, context: Context): List<ShortPlaceWithAddress> {
//        val places =
//            if (::allPlaces.isInitialized) allPlaces
//            else placesDao.getAllPlaces()
//        val distanceMarkerMap: HashMap<Float, ShortPlace> = HashMap()
//        val startLocation = Location("start location")
//        startLocation.latitude = location.latitude
//        startLocation.longitude = location.longitude
//
//        places.forEach{ place ->
//            val endLocation = Location("end location")
//            endLocation.latitude = place.latitude
//            endLocation.longitude = place.longitude
//            distanceMarkerMap[startLocation.distanceTo(endLocation)] = place
//        }
//
//        val sortedPlaces = distanceMarkerMap.toSortedMap().values
//
//        return sortedPlaces.take(5).map { place ->
//            val address = getAddress(place, context)
//            ShortPlaceWithAddress(place.places_id, place.place_name, place.coordinates, address, place.type_of_place)
//        }
        TODO()
    }

    @WorkerThread
    override suspend fun getFiveBestFittingPlacesAsync(name: String, context: Context): List<ShortPlaceWithAddress?> {
//        val places =
//            if (::allPlaces.isInitialized) allPlaces
//            else placesDao.getAllPlaces()
//        val namesMarkerMap: HashMap<String, ShortPlace> = HashMap()
//        val addressMarkerMap: HashMap<String, ShortPlace> = HashMap()
//
//        val placesNames = places.map { place ->
//            namesMarkerMap[place.place_name] = place
//            place.place_name
//        }
//
//        val bestFiveWithRespectToNameWithResults = FuzzySearch.extractTop(name, placesNames, 5)
//
//        val placesAddresses = places.map { place ->
//            val address = getAddress(place, context)
//            if (address != null){
//                addressMarkerMap[address] = place
//            }
//            address
//        }.filter { place -> place != null }
//
//        var bestFiveWithRespectToAddressWithResults = listOf<ExtractedResult>()
//
//        if (!placesAddresses.isNullOrEmpty()){
//            bestFiveWithRespectToAddressWithResults = FuzzySearch.extractTop(name, placesAddresses, 5)
//        }
//
//        val bestFiveWithResults = bestFiveWithRespectToAddressWithResults.union(bestFiveWithRespectToNameWithResults)
//
//        val bestFiveSorted = bestFiveWithResults.sortedWith(CompareObjects)
//
//        return bestFiveSorted.map { place ->
//            if( place.string in namesMarkerMap.keys){
//                val shortPlace = namesMarkerMap[place.string]
//                return@map ShortPlaceWithAddress(
//                    shortPlace!!.places_id,
//                    shortPlace.place_name,
//                    shortPlace.coordinates,
//                    getAddress(shortPlace, context),
//                    shortPlace.type_of_place
//                )
//            }
//            else{
//                val shortPlace = addressMarkerMap[place.string]
//                return@map ShortPlaceWithAddress(
//                    shortPlace!!.places_id,
//                    shortPlace.place_name,
//                    shortPlace.coordinates,
//                    place.string,
//                    shortPlace.type_of_place
//                )
//            }
//        }
        TODO()
    }

    private fun getAddress(place: ShortPlace, context: Context): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>? =
            try{
                geocoder.getFromLocation(place.latitude, place.longitude, 1)
            }
            catch(e:Exception){
                null
            }
        val address = addresses?.map{
                item -> item.getAddressLine(0)?.toString()
        }
        return if (address != null){
            val commaIndex = address[0]!!.indexOf(",")
            address[0]?.subSequence(0, commaIndex).toString()
        } else {
            null
        }
    }
}