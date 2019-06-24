package com.kotlin.zerowasteappvol1.activities.helpers

import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.database.ShortPlace

fun getMarkerIcon (typeOfPlace: String?): Int{
    return when (typeOfPlace){
        "sklep" -> R.drawable.ic_marker_icon_yellow
        "restauracja" -> R.drawable.ic_marker_icon_green
        "punkt naprawczy" -> R.drawable.ic_marker_icon_blue
        else -> R.drawable.ic_marker_icon_plain
    }
}