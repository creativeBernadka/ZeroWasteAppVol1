package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import com.kotlin.zerowasteappvol1.presenter.PlacesViewModel
import com.kotlin.zerowasteappvol1.presenter.PlacesViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PlacesViewModelModule {

    @Provides
    @Singleton
    fun providesPlacesViewModel(app: Application): PlacesViewModel = PlacesViewModelImpl(app)
}