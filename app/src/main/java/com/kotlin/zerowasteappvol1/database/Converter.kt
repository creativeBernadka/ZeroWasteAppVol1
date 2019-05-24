package com.kotlin.zerowasteappvol1.database

import android.arch.persistence.room.TypeConverter
import java.net.URL
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun urlToString(url: URL?): String?{
        return url.toString()
    }

    @TypeConverter
    fun stringToUrl(string: String?): URL?{
        return URL(string)
    }
}