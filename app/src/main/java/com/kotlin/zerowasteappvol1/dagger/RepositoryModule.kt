package com.kotlin.zerowasteappvol1.dagger

import com.kotlin.zerowasteappvol1.repository.PlacesRepository
import com.kotlin.zerowasteappvol1.repository.PlacesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePlacesRepository(): PlacesRepository =
        PlacesRepositoryImpl()
}