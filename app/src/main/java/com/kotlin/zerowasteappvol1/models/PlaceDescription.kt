package com.kotlin.zerowasteappvol1.models


data class PlaceDescription(var name: String) {
    var rating: Double? = null
    var typeOfPlace: String? = null
    var startHour: String? = null
    var endHour: String? = null
    var address: String? = null
    var phoneNumber: String? = null
    var website: String? = null
}