package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import com.kotlin.zerowasteappvol1.database.PlacesDao
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.kotlin.zerowasteappvol1.database.PlacesRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providePlacesRoomDatabase(app: Application): PlacesRoomDatabase =
        Room
            .databaseBuilder(app,PlacesRoomDatabase::class.java, "places_db.db")
//            .addCallback(
//                class PlacesDatabaseCallback(
//                val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//                {
//                override fun onOpen(db: SupportSQLiteDatabase) {
//                    super.onOpen(db)
//                    PlacesRoomDatabase.INSTANCE?.let { database ->
//                        scope.launch(Dispatchers.IO) {
//                            populateDatabase(database.placesDao())
//                        }
//                    }
//                }
//            })
            .build()

    @Provides
    @Singleton
    fun providePlacesDao(database: PlacesRoomDatabase, application: Application, scope: CoroutineScope):
            PlacesDao = PlacesRoomDatabase.getDatabase(application, scope).placesDao()
}