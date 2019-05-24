package com.kotlin.zerowasteappvol1.database

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

    @Insert
    fun insertImage(imagesUrl: ImagesUrl)

    @Query("SELECT name, latitude, longitude FROM places")
    fun getAllPlaces(): List<ShortPlace>

    @Query("SELECT places.name, places.latitude, places.longitude, places.rating, places.type_of_place, " +
            "opening_hours.startHour, opening_hours.endHour, opening_hours.weekday " +
            "FROM places INNER JOIN opening_hours ON places.id = opening_hours.placeId " +
            "WHERE( name = :name and latitude = :latitude and longitude = :longitude and weekday = :dayOfWeek) LIMIT 1")
    fun getMarkerDescription(name: String, latitude: Double, longitude: Double, dayOfWeek: Int): PlaceDescription

    @Query("SELECT images_url.url FROM images_url INNER JOIN places ON places.id = images_url.placeId " +
            "WHERE( places.name = :name and places.latitude = :latitude and places.longitude = :longitude)")
    fun getMarkerImages(name: String, latitude: Double, longitude: Double): List<String>

//    Przerobic tak, zeby podawalo wszystkie godziny otwarcia
    @Query("SELECT * FROM places WHERE( name = :name and latitude = :latitude and longitude = :longitude) LIMIT 1")
    fun getMarkerDetails(name: String, latitude: Double, longitude: Double): LiveData<Place>
}