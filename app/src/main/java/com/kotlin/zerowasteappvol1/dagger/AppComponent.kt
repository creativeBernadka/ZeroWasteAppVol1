package com.kotlin.zerowasteappvol1.dagger

import com.kotlin.zerowasteappvol1.UI.MapsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ApiModule::class])
interface AppComponent {
    fun inject(target: MapsActivity)
}