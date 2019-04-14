package com.kotlin.zerowasteappvol1

import com.google.android.gms.maps.model.LatLng
import junit.framework.Assert.assertSame
import org.junit.Test


class MarkerRepositoryTest {

    @Test
    fun checkIfGetAllDataReturnsArrayOfPlaces(){
        val repository = Repository()
        val expected: Array<Places> = arrayOf(Places("", LatLng(0.0, 0.0)))
        assertSame(expected::class, repository.getAllDataAsync()::class)
    }
}