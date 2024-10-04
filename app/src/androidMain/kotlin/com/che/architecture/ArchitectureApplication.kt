package com.che.architecture

import android.app.Application
import com.che.architecture.data.common.di.repositoriesModule
import com.che.architecture.di.applicationModule
import org.koin.core.context.startKoin

class ArchitectureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                applicationModule,
                repositoriesModule
            )
        }
    }
}
