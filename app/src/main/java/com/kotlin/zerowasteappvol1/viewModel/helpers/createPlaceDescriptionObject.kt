package com.kotlin.zerowasteappvol1.viewModel.helpers

import com.kotlin.zerowasteappvol1.models.PlaceDescription
import com.kotlin.zerowasteappvol1.models.PlaceDescriptionWithImages

fun createPlaceDescriptionObject(placeToShorten: PlaceDescriptionWithImages): PlaceDescription{
    val shortenedPlace = PlaceDescription(placeToShorten.place_name)
    shortenedPlace.rating = placeToShorten.rating
    shortenedPlace.typeOfPlace = placeToShorten.type_of_place
    shortenedPlace.startHour = placeToShorten.start_hour
    shortenedPlace.endHour = placeToShorten.end_hour
    shortenedPlace.address = placeToShorten.address
    shortenedPlace.phoneNumber = placeToShorten.phone_number
    shortenedPlace.website = placeToShorten.website
    return shortenedPlace
}