package com.kotlin.zerowasteappvol1

import com.google.android.gms.maps.model.LatLng

class Repository: MarkerRepository {

    override fun getAllData(): Array<Places> {
        return arrayOf(Places("Place 1", LatLng(49.97232, 19.03432), "sfsdf", "23243243"))
    }

    override fun addInstance(place: Places) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findInCategory(category: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findNearby(spot: LatLng) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeInstance(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}