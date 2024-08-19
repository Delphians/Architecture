package com.che.architecture

import android.app.Application
import com.che.architecture.di.ApplicationModule

internal class ArchitectureApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationModule.buildPaymentsModule()
    }
}
