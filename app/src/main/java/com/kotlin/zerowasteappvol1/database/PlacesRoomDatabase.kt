package com.kotlin.zerowasteappvol1.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
                ).addCallback(PlacesDatabaseCallback(scope)).addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3).addMigrations(MIGRATION_3_4).build()
                INSTANCE = instance
                return instance
            }
        }

        private class PlacesDatabaseCallback(
            private val scope: CoroutineScope
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

//                var place = Place(1, "Miejsce 1",  49.835543, 19.076082, 5.0,
//                    "sklep","123456789", "To jest miejsce 1", website = null)
//                placesDao.insertPlace(place)
//                place = Place(2, "Miejsce 2", 49.83455, 19.077633, 2.5,
//                    "restauracja","123456789", "To jest miejsce 2", website = null)
//                placesDao.insertPlace(place)
//                place = Place(3, "Miejsce 3",  49.834240, 19.079626, 3.0,
//                    "sklep","123456789", "To jest miejsce 3", website = null)
//                placesDao.insertPlace(place)
//
//                var openingHours = OpeningHours(1,1, 2, "8:00", "15:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(2,1, 3, "8:00", "17:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(3, 2, 4, "10:00", "17:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(4, 2, 6, "8:00", "14:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(5, 3, 7, "9:00", "17:00")
//                placesDao.insertHour(openingHours)
//
//                var imageUrl = ImagesUrl(1, 1, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(2, 2, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(3, 2, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(4, 2, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(5, 3, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(5, 3, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(7, 3, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(8, 3, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
//                imageUrl = ImagesUrl(9, 3, "https://bit.ly/2WbBDkJ")
//                placesDao.insertImage(imageUrl)
            }
        }
    }
}