package com.kotlin.zerowasteappvol1.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.*
import android.content.Context
import com.kotlin.zerowasteappvol1.application.ZeroWasteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
//                    "sklep","123456789", "To jest miejsce 1", website = "https://developer.android.com")
//                placesDao.insertPlace(place)
//                place = Place(2, "Miejsce 2", 49.83455, 19.077633, 2.5,
//                    "restauracja","456789123", "To jest miejsce 2", website = "http://sklepbezpudla.pl")
//                placesDao.insertPlace(place)
//                place = Place(3, "Miejsce 3",  49.834240, 19.079626, 3.0,
//                    "sklep","789123456", "To jest miejsce 3", website = "http://eloquentjavascript.net")
//                placesDao.insertPlace(place)
//
//                var openingHours = OpeningHours(1,1, 1, "8:00", "15:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(2,1, 2, "8:05", "15:05")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(3,1, 3, "8:10", "15:10")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(4,1, 4, "8:15", "15:15")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(5,1, 5, "8:20", "15:20")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(6,1, 6, "8:25", "15:25")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(7,1, 7, "8:30", "15:30")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(8, 2, 1, "9:00", "16:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(9, 2, 2, "9:05", "16:05")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(10, 2, 3, "9:10", "16:10")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(11, 2, 4, "9:15", "16:15")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(12, 2, 5, "9:20", "16:20")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(13, 2, 6, "9:25", "16:25")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(14, 2, 7, "9:30", "16:30")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(15, 3, 1, "10:00", "17:00")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(16, 3, 2, "10:05", "17:05")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(17, 3, 3, "10:10", "17:10")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(18, 3, 4, "10:15", "17:15")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(19, 3, 5, "10:20", "17:20")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(20, 3, 6, "10:25", "17:25")
//                placesDao.insertHour(openingHours)
//                openingHours = OpeningHours(21, 3, 7, "10:30", "17:30")
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
//                imageUrl = ImagesUrl(6, 3, "https://bit.ly/2WbBDkJ")
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