package com.kotlin.zerowasteappvol1.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration


val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE opening_hours")
        database.execSQL("CREATE TABLE opening_hours(" +
                "id INTEGER NOT NULL PRIMARY KEY, placeId INTEGER NOT NULL, weekday INTEGER NOT NULL, startHour TEXT NOT NULL, endHour TEXT NOT NULL, " +
                "FOREIGN KEY (placeId) REFERENCES places(id)) ")
    }
}