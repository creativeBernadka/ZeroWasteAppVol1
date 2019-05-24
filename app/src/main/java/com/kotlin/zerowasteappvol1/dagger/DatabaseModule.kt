package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import android.arch.persistence.room.Database
import com.kotlin.zerowasteappvol1.database.PlacesDao
import dagger.Binds
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import android.arch.persistence.room.Room
import com.kotlin.zerowasteappvol1.database.PlacesRoomDatabase


@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideCryptocurrenciesDatabase(app: Application): PlacesRoomDatabase = Room.databaseBuilder(app,
        PlacesRoomDatabase::class.java, "places_db").build()

    @Provides
    @Singleton
    fun provideCryptocurrenciesDao(database: Database): PlacesDao = database.placesDao()
}