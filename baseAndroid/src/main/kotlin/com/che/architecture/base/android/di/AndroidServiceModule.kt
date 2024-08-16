package com.che.architecture.base.android.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.che.architecture.base.android.service.lifecycle.AndroidLifecycleService
import com.che.architecture.base.android.service.lifecycle.AndroidLifecycleServiceImpl

object AndroidServiceModule {

    fun provideApplicationLifecycleService(): AndroidLifecycleService =
        AndroidLifecycleServiceImpl(provideProcessLifecycleOwner())

    private fun provideProcessLifecycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()
}
