package com.kotlin.zerowasteappvol1.dagger

import android.app.Application
import com.kotlin.zerowasteappvol1.repository.PlacesRepository
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModel
import com.kotlin.zerowasteappvol1.viewModel.PlacesViewModelImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesPlacesViewModel(app: Application, repository: PlacesRepository, scope: CoroutineScope):
            PlacesViewModel = PlacesViewModelImpl(app, repository, scope)
//    = PlacesViewModelMock(app)

}