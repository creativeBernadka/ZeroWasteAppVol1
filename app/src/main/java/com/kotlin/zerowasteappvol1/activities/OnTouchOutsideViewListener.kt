package com.kotlin.zerowasteappvol1.activities

import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.kotlin.zerowasteappvol1.R

class OnTouchOutsideViewListener{
    fun onTouchOutside(view: View, event: MotionEvent, activity: MapsActivity) {
        activity.findViewById<LinearLayout>(R.id.linearLayout_carousel_images).removeAllViews()
        view.visibility = View.GONE
    }
}