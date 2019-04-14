package com.kotlin.zerowasteappvol1

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MarkerRepositoryMock: MarkerRepository, CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override suspend fun getAllDataAsync(): Array<Places> {
        delay(1000)
        return arrayOf(Places("Miejsce 1", LatLng(49.83455, 19.077633), "Orchidei 22H",
            "123456789"), Places("Miejsce 2", LatLng( 49.835543, 19.076082), "Ulica",
            "123456789"), Places("Miejsce 3", LatLng( 49.834240, 19.079626), "Inna ulica",
            "123456789"))
    }

    override fun addInstance(place: Places) {
        TODO("not implemented")
    }

    override fun findInCategory(category: String) {
        TODO("not implemented")
    }

    override fun findNearby(spot: LatLng) {
        TODO("not implemented")
    }

    override fun removeInstance(index: Int) {
        TODO("not implemented")
    }
}