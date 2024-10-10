package com.che.architecture

import android.app.Application
import com.che.architecture.data.common.di.repositoriesModule
import com.che.architecture.di.applicationModule
import org.koin.core.context.startKoin
import co.touchlab.kermit.koin.KermitKoinLogger
import co.touchlab.kermit.Logger

class ArchitectureApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(
                KermitKoinLogger(Logger.withTag("koin")),
            )
            modules(
                applicationModule,
                repositoriesModule
            )
        }
    }
}
