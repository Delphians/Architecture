package com.che.architecture

import com.che.architecture.data.common.di.repositoriesModule
import com.che.architecture.di.applicationModule
import org.koin.core.context.startKoin
import kotlin.time.measureTime
import co.touchlab.kermit.koin.KermitKoinLogger
import co.touchlab.kermit.Logger.Companion.withTag

fun initKoin() {
    @Suppress("UnusedPrivateProperty")
    val timeTaken = measureTime {
        startKoin {
            logger(
                KermitKoinLogger(withTag("koin")),
            )
            modules(
                applicationModule,
                repositoriesModule
            )
        }
    }.inWholeMilliseconds
}
