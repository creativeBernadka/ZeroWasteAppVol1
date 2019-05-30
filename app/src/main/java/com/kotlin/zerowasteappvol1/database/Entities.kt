package com.kotlin.zerowasteappvol1.database

import android.arch.persistence.room.*
import com.google.android.gms.maps.model.LatLng


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

@Entity (
    tableName = "images_url",
    foreignKeys = [ForeignKey(entity = Place::class, parentColumns = ["id"], childColumns = ["placeId"])]
)
data class ImagesUrl(
    @PrimaryKey val id: Int,
    val placeId: Int,
    val url: String
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
    val rating: Double?,
    @ColumnInfo(name = "type_of_place") val typeOfPlace: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "website") val website: String?,
    @ColumnInfo(name = "startHour") val startHour: String,
    @ColumnInfo(name = "endHour") val endHour: String,
    @ColumnInfo(name = "weekday") val dayOfWeek: Int
)

