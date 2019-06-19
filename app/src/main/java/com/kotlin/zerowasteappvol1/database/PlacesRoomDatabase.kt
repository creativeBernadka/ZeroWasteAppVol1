package com.kotlin.zerowasteappvol1.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.content.Context
import android.util.Log
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileReader
import java.io.File
import java.io.InputStreamReader
import java.util.Collections.replaceAll
import java.util.regex.Pattern
import javax.inject.Inject


@Database(entities = [Place::class, OpeningHours::class, ImagesUrl::class], version = 4, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PlacesRoomDatabase: RoomDatabase() {

    abstract fun placesDao(): PlacesDao


    companion object{
        @Volatile
        private var INSTANCE: PlacesRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PlacesRoomDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlacesRoomDatabase::class.java,
                    "Places_database"
                ).addCallback(PlacesDatabaseCallback(scope, context.applicationContext)).addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3).addMigrations(MIGRATION_3_4).build()
                INSTANCE = instance
                return instance
            }
        }

        private class PlacesDatabaseCallback(
            private val scope: CoroutineScope,
            val context: Context
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.placesDao())
                    }
                }
            }

            fun populateDatabase(placesDao: PlacesDao){

//                populatePlaces(placesDao)
//                populateHours(placesDao)
//                populateImages(placesDao)
            }

            private fun populatePlaces(placesDao: PlacesDao){
                val csvStream = context.assets.open("places.csv")
                val csvStreamReader = InputStreamReader(csvStream)
                val reader = CSVReader(csvStreamReader)
                var nextLine: Array<String>? = reader.readNext()
                while ( nextLine != null) {
                    placesDao.insertPlace(
                        Place(
                            nextLine[0].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[1],
                            nextLine[2].toDouble(),
                            nextLine[3].toDouble(),
                            nextLine[4].toDouble(),
                            nextLine[5],
                            nextLine[6],
                            nextLine[7],
                            nextLine[8]
                        )
                    )
                    nextLine = reader.readNext()
                }
            }

            private fun populateHours(placesDao: PlacesDao){
                val csvStream = context.assets.open("opening_hours.csv")
                val csvStreamReader = InputStreamReader(csvStream)
                val reader = CSVReader(csvStreamReader)
                var nextLine: Array<String>? = reader.readNext()
                while ( nextLine != null) {
                    placesDao.insertHour(
                        OpeningHours(
                            nextLine[0].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[1].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[2].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[3],
                            nextLine[4]
                        )
                    )
                    nextLine = reader.readNext()
                }
            }

            private fun populateImages(placesDao: PlacesDao){
                val csvStream = context.assets.open("images_url.csv")
                val csvStreamReader = InputStreamReader(csvStream)
                val reader = CSVReader(csvStreamReader)
                var nextLine: Array<String>? = reader.readNext()
                while ( nextLine != null) {
                    placesDao.insertImage(
                        ImagesUrl(
                            nextLine[0].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[1].replace(Regex("[^ -~]+"), "").toInt(),
                            nextLine[2]
                        )
                    )
                    nextLine = reader.readNext()
                }
            }
        }
    }
}