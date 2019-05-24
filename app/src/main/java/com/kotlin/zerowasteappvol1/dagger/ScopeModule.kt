package com.kotlin.zerowasteappvol1.dagger

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class ScopeModule {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob
    private val scope = CoroutineScope(coroutineContext)

    @Provides
    @Singleton
    fun provideScope(): CoroutineScope = scope
}