package com.kotlin.zerowasteappvol1.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlacesDao {

    @Insert
    fun insertPlace(place: Place)

    @Insert
    fun insertHour(openingHours: OpeningHours)

    @Query("SELECT name, latitude, longitude FROM places")
    fun getAllPlaces(): LiveData<List<ShortPlace>>

    @Query("SELECT places.name, places.latitude, places.longitude, places.rating, places.type_of_place, " +
            "opening_hours.startHour, opening_hours.endHour " +
            "FROM places INNER JOIN opening_hours ON places.id = opening_hours.placeId " +
            "WHERE( name = :name and latitude = :latitude and longitude = :longitude) LIMIT 1")
    fun getMarkerDescription(name: String, latitude: Double, longitude: Double): LiveData<PlaceDescription>

//    Przerobic tak, zeby podawalo wszystkie godziny otwarcia
    @Query("SELECT * FROM places WHERE( name = :name and latitude = :latitude and longitude = :longitude) LIMIT 1")
    fun getMarkerDetails(name: String, latitude: Double, longitude: Double): LiveData<Place>
}