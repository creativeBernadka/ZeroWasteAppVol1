package com.kotlin.zerowasteappvol1.activities.helpers

import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.kotlin.zerowasteappvol1.R
import com.kotlin.zerowasteappvol1.activities.MapsActivity

class DescriptionOperations(private val activity: MapsActivity) {

    private val INITIAL_ITEMS_COUNT = 3.5f

    fun displayImages(images: List<Drawable?>){
        val linearLayoutCarouselImages = activity.findViewById<LinearLayout>(R.id.linearLayout_carousel_images)
        val cardViewCarouselImages = activity.findViewById<CardView>(R.id.cardView_carousel_images)

        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val imageWidth = (displayMetrics.widthPixels / INITIAL_ITEMS_COUNT).toInt()
        var imageItem: ImageView
        var countNulls = 0
        images.map {item ->
            imageItem = ImageView(activity)
            if(item == null) countNulls += 1
            else{
                imageItem.setImageDrawable(item)
                val linearLayout = LinearLayout.LayoutParams(imageWidth, imageWidth)
                linearLayout.rightMargin = 2
                imageItem.layoutParams = linearLayout
                linearLayoutCarouselImages.addView(imageItem)
            }
        }
        if (countNulls != images.size) cardViewCarouselImages.visibility = View.VISIBLE
    }
}