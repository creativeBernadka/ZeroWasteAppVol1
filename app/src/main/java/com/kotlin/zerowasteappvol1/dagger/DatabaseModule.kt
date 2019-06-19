package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback
import android.os.AsyncTask.execute
import android.support.annotation.NonNull
import com.kotlin.zerowasteappvol1.database.*


@Module
class DatabaseModule {

//    @Singleton
//    @Provides
//    fun providePlacesRoomDatabase(app: Application): PlacesRoomDatabase {
//        return Room.databaseBuilder(
//            app,
//            PlacesRoomDatabase::class.java,
//            "mydb.db"
//        )
//            .addCallback(object : Callback {
//                fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    Executors.newSingleThreadScheduledExecutor().execute(Runnable {
//                        // insert data using DAO
//                    })
//                }
//            }).addMigrations(MIGRATION_1_2)
//            .addMigrations(MIGRATION_2_3).addMigrations(MIGRATION_3_4)
//            .build()
//    }

    @Provides
    @Singleton
    fun providePlacesRoomDatabase(app: Application): PlacesRoomDatabase =
        Room
            .databaseBuilder(app,PlacesRoomDatabase::class.java, "places_db.db")
            .build()

    @Provides
    @Singleton
    fun providePlacesDao(database: PlacesRoomDatabase, application: Application, scope: CoroutineScope):
            PlacesDao = PlacesRoomDatabase.getDatabase(application, scope).placesDao()
}