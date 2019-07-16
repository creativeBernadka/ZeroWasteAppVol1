package com.kotlin.zerowasteappvol1.models

data class PlaceDescription(
    val name: String,
    val rating: Double?,
    val typeOfPlace: String?,
    val phoneNumber: String?,
    val website: String?,
    val startHour: String,
    val endHour: String,
    val dayOfWeek: Int
)