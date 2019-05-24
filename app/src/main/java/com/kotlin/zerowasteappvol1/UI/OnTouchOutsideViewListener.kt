package com.kotlin.zerowasteappvol1.UI

import android.view.MotionEvent
import android.view.View

class OnTouchOutsideViewListener{
    fun onTouchOutside(view: View, event: MotionEvent) {
        view.visibility = View.GONE
    }
}