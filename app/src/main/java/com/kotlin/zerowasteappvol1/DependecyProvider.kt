package com.kotlin.zerowasteappvol1

import com.google.android.gms.maps.model.LatLng
import dagger.Component
import dagger.Module
import dagger.Provides



@Module
class DependecyProvider {

    @Provides
    fun provideMarkerRepositoryMock(): MarkerRepository{
        return MarkerRepositoryMock()
    }
}

@Component(modules = [DependecyProvider::class])
interface MapComponent{
    fun inject(app:MapsActivity)
}

class MarkerRepositoryMock: MarkerRepository{

    override fun getAllData(): Array<Places> {
        return arrayOf(Places("Place 1", LatLng(49.97232, 19.03432), "sfsdf", "23243243"))
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