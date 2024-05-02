package com.che.architecture.base.android.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.che.architecture.base.android.service.lifecycle.AndroidLifecycleService
import com.che.architecture.base.android.service.lifecycle.AndroidLifecycleServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AndroidServiceModule {

    @Binds
    internal abstract fun bindsApplicationLifecycleService(
        it: AndroidLifecycleServiceImpl
    ): AndroidLifecycleService

    companion object {
        @Provides
        fun provideProcessLifecycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()
    }
}
