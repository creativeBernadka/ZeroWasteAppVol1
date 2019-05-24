package com.kotlin.zerowasteappvol1.application

import android.app.Application
import com.kotlin.zerowasteappvol1.dagger.AppComponent
import com.kotlin.zerowasteappvol1.dagger.DaggerAppComponent

class ZeroWasteApplication: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}