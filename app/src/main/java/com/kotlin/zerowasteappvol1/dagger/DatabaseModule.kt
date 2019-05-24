package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import com.kotlin.zerowasteappvol1.database.PlacesDao
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import android.arch.persistence.room.Room
import com.kotlin.zerowasteappvol1.database.PlacesRoomDatabase
import kotlinx.coroutines.CoroutineScope


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providePlacesRoomDatabase(app: Application): PlacesRoomDatabase = Room.databaseBuilder(app,
        PlacesRoomDatabase::class.java, "places_db").build()

    @Provides
    @Singleton
    fun providePlacesDao(database: PlacesRoomDatabase, application: Application, scope: CoroutineScope):
            PlacesDao = PlacesRoomDatabase.getDatabase(application, scope).placesDao()
}