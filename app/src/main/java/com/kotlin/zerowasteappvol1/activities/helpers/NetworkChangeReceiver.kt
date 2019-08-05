package com.kotlin.zerowasteappvol1.activities.helpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager


class NetworkChangeReceiver: BroadcastReceiver() {

    private lateinit var networkChangeReceiverListener: NetworkChangeReceiverListener
    private var connected = false

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected

        connected = isConnected

        if (::networkChangeReceiverListener.isInitialized) {
            networkChangeReceiverListener.onNetworkChange(isConnected)
        }
    }

    fun setListener(listener: NetworkChangeReceiverListener){
        networkChangeReceiverListener = listener
    }

    fun isConnected(): Boolean{
        return connected
    }

    interface NetworkChangeReceiverListener{

        fun onNetworkChange(isConnected: Boolean)
    }
}