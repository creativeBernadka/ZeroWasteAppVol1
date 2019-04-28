package com.kotlin.zerowasteappvol1.UI

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
        finish()
    }

}