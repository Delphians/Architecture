package com.che.architecture

import com.che.architecture.data.common.di.repositoriesModule
import com.che.architecture.di.applicationModule
import org.koin.core.context.startKoin
import kotlin.time.measureTime

fun initKoin() {
    @Suppress("UnusedPrivateProperty")
    val timeTaken = measureTime {
        startKoin {
            modules(
                applicationModule,
                repositoriesModule
            )
        }
    }.inWholeMilliseconds
}
