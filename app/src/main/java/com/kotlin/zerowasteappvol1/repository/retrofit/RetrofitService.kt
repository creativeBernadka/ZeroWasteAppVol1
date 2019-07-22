package com.kotlin.zerowasteappvol1.repository.retrofit

import com.kotlin.zerowasteappvol1.models.Description
import com.kotlin.zerowasteappvol1.models.ListOfShortPlaces
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("places")
    suspend fun getAllPlaces(): ListOfShortPlaces

    @GET("places/{places_id}")
    suspend fun getPlaceDescription(@Path("places_id") id: Int, @Query("dayOfWeek") dayOfWeek: Int = 1): Description
}