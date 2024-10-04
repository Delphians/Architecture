package com.che.architecture.data.remote.datasource.di

import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSource
import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSourceImpl
import com.che.architecture.data.remote.datasource.tiingo.TiingoUrlBuilder
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<TiingoDataSource> {
        TiingoDataSourceImpl(
            tiingoUrlBuilder = get(),
            ktorClient = get()
        )
    }

    factory { (tiingoBaseUrl: String, tiingoToken: String) ->
        TiingoUrlBuilder(tiingoBaseUrl, tiingoToken)
    }
}
