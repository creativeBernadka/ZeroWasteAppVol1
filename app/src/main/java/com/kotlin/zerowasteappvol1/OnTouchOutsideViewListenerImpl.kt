package com.kotlin.zerowasteappvol1

import android.view.MotionEvent
import android.view.View

class OnTouchOutsideViewListenerImpl: OnTouchOutsideViewListener {
    override fun onTouchOutside(view: View, event: MotionEvent) {
        view.visibility = View.GONE
    }
}