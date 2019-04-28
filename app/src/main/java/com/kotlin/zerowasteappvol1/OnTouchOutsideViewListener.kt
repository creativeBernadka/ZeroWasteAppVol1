package com.kotlin.zerowasteappvol1

import android.view.MotionEvent
import android.view.View

interface OnTouchOutsideViewListener {

    fun onTouchOutside(view: View, event: MotionEvent)
}