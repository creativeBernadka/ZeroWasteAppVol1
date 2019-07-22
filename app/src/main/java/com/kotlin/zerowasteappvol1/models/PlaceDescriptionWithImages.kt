package com.kotlin.zerowasteappvol1.models

data class PlaceDescriptionWithImages(
    val place_name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double,
    val type_of_place: String,
    val day_of_week: Int,
    val start_hour: String,
    val end_hour: String,
    val images: List<Image>,
    val phone_number: String,
    val website: String,
    val address: String
)