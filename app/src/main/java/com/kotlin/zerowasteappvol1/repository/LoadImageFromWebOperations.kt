package com.kotlin.zerowasteappvol1.repository

import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL

fun loadImageFromWebOperations(url: String): Drawable? {
    return try {
        val `is` = URL(url).content as InputStream
        Drawable.createFromStream(`is`, "src name")
    } catch (e: Exception) {
        null
    }

}