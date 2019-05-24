package com.kotlin.zerowasteappvol1.dagger

import dagger.Component
import android.app.Application
import com.kotlin.zerowasteappvol1.UI.MapsActivity
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, RepositoryModule::class, DatabaseModule::class, ScopeModule::class])
interface AppComponent {

    fun inject(target: MapsActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}