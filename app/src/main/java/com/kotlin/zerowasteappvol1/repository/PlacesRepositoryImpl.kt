package com.kotlin.zerowasteappvol1.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.graphics.drawable.Drawable
import android.location.Address
import android.support.annotation.WorkerThread
import com.kotlin.zerowasteappvol1.database.Place
import com.kotlin.zerowasteappvol1.database.PlaceDescription
import com.kotlin.zerowasteappvol1.database.PlacesDao
import com.kotlin.zerowasteappvol1.database.ShortPlace
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.kotlin.zerowasteappvol1.viewModel.ShortPlaceWithAddress


class PlacesRepositoryImpl @Inject constructor(private val placesDao: PlacesDao):
    PlacesRepository {

    @WorkerThread
    override suspend fun getAllPlacesAsync(): List<ShortPlace>{
        delay(2000)
        return placesDao.getAllPlaces()
    }

    @WorkerThread
    override suspend fun getMarkerDescriptionAsync(shortPlace: ShortPlace?, context: Context)
            : PlaceDescriptionWithAddress?{
        delay(500)

        val name = shortPlace!!.name
        val coordinates = shortPlace.coordinates
        val calendar = Calendar.getInstance()
        val dayOfWeek =
            when (calendar.get(Calendar.DAY_OF_WEEK)){
                Calendar.MONDAY -> 1
                Calendar.TUESDAY -> 2
                Calendar.WEDNESDAY -> 3
                Calendar.THURSDAY -> 4
                Calendar.FRIDAY -> 5
                Calendar.SATURDAY -> 6
                Calendar.SUNDAY -> 7
                else -> 1
            }

        val markerDescription = placesDao.getMarkerDescription(name, coordinates.latitude, coordinates.longitude, dayOfWeek)
        val placeDescriptionWithAddress = PlaceDescriptionWithAddress(name)

        placeDescriptionWithAddress.rating = markerDescription.rating
        placeDescriptionWithAddress.typeOfPlace = markerDescription.typeOfPlace
        placeDescriptionWithAddress.startHour = markerDescription.startHour
        placeDescriptionWithAddress.endHour = markerDescription.endHour
        placeDescriptionWithAddress.phoneNumber = markerDescription.phoneNumber
        placeDescriptionWithAddress.website = markerDescription.website

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>? =
            try{
                geocoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)
            }
            catch(e:Exception){
                null
            }

        placeDescriptionWithAddress.address =
            if (addresses != null){
                addresses.map{item -> item.getAddressLine(0)?.toString()}[0]
            }
            else{
                null
            }

        return placeDescriptionWithAddress
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

    @WorkerThread
    override suspend fun getFiveNearestPlacesAsync(location: LatLng, context: Context): List<ShortPlaceWithAddress> {
        val places = placesDao.getAllPlaces()
        val distanceMarkerMap: HashMap<Float, ShortPlace> = HashMap()
        val startLocation = Location("start location")
        startLocation.latitude = location.latitude
        startLocation.longitude = location.longitude

        places.forEach{ place ->
            val endLocation = Location("end location")
            endLocation.latitude = place.latitude
            endLocation.longitude = place.longitude
            distanceMarkerMap[startLocation.distanceTo(endLocation)] = place
        }

        val sortedPlaces = distanceMarkerMap.toSortedMap().values

        val sortedPlacesWithAddress = sortedPlaces.take(5).map {place ->
            val address = getAddress(place, context)
            ShortPlaceWithAddress(place.name, address)
        }

        return sortedPlacesWithAddress
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
        val address = addresses?.map{item -> item.getAddressLine(0)?.toString()}
        return if (address != null){
            address[0]
        } else {
            null
        }
    }
}