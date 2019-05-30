package com.kotlin.zerowasteappvol1.UI

import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.kotlin.zerowasteappvol1.R
import kotlinx.android.synthetic.main.activity_maps.view.*

class OnTouchOutsideViewListener{
    fun onTouchOutside(view: View, event: MotionEvent, activity: MapsActivity) {
        activity.findViewById<LinearLayout>(R.id.linearLayout_carousel_images).removeAllViews()
        view.visibility = View.GONE
    }
}