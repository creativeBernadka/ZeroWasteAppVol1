package com.kotlin.zerowasteappvol1.UI

import android.view.MotionEvent
import android.view.View
import com.kotlin.zerowasteappvol1.OnTouchOutsideViewListener

class OnTouchOutsideViewListenerImpl: OnTouchOutsideViewListener {
    override fun onTouchOutside(view: View, event: MotionEvent) {
        view.visibility = View.GONE

    }
}