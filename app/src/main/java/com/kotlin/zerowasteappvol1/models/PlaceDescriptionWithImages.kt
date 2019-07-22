package com.kotlin.zerowasteappvol1.models

data class PlaceDescriptionWithImages(
    val day_of_week: Int,
    val end_hour: String,
    val images: List<Image>,
    val phone_number: String,
    val place_name: String,
    val rating: Double,
    val start_hour: String,
    val type_of_place: String,
    val website: String,
    val address: String
)