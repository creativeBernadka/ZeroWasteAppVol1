package com.kotlin.zerowasteappvol1

import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL

suspend fun loadImageFromWebOperations(url: String): Drawable? {
    try {
        val `is` = URL(url).getContent() as InputStream
        return Drawable.createFromStream(`is`, "src name")
    } catch (e: Exception) {
        return null
    }

}