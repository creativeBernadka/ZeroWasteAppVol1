package com.kotlin.zerowasteappvol1.repository.retrofit

import com.kotlin.zerowasteappvol1.models.ListOfShortPlaces
import com.kotlin.zerowasteappvol1.models.PlaceDescription
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("places")
    suspend fun getAllPlaces(): ListOfShortPlaces

    @GET("places/{places_id}")
    suspend fun getPlaceDescription(@Path("places_id") id: String): PlaceDescription
}