package com.che.architecture.data.remote.datasource.di

import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSource
import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSourceImpl
import com.che.architecture.data.remote.datasource.tiingo.TiingoUrlBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteDataSourceModule = module {

    includes(ktorModule)

    single<TiingoDataSource> {
        TiingoDataSourceImpl(
            tiingoUrlBuilder = get(),
            ktorClient = get()
        )
    }

    factory {
        TiingoUrlBuilder(get(named("tiingoBaseUrl")), get(named("tiingoToken")))
    }
}
