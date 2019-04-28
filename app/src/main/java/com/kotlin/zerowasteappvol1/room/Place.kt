package com.kotlin.zerowasteappvol1.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.util.*



@Entity(tableName = "places")
data class Place(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "type_of_place") val typeOfPlace: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "website") val website: String?
){
    val coordinates: LatLng
        get() = LatLng(latitude, longitude)
}

@Entity(
    tableName = "opening_hours",
    foreignKeys = [ForeignKey(entity = Place::class, parentColumns = ["id"], childColumns = ["placeId"])]
)
data class OpeningHours(
    @PrimaryKey val id: Int, //dodac autoincrement
    val placeId: Int,
    val weekday: Int,
    val startHour: String,
    val endHour: String
)

data class ShortPlace(
    val name: String,
    val latitude: Double,
    val longitude: Double
){
    val coordinates: LatLng
        get() = LatLng(latitude, longitude)
}

data class PlaceDescription(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double?,
    @ColumnInfo(name = "type_of_place") val typeOfPlace: String?,
    @ColumnInfo(name = "startHour") val startHour: Double,
    @ColumnInfo(name = "endHour") val endHour: Double
){
    val coordinates: LatLng
        get() = LatLng(latitude, longitude)
}

