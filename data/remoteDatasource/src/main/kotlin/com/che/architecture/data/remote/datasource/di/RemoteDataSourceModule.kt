package com.che.architecture.data.remote.datasource.di

import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSource
import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSourceImpl
import com.che.architecture.data.remote.datasource.tiingo.TiingoUrlBuilder

object RemoteDataSourceModule {

    fun provideTiingoDataSource(
        tiingoBaseUrl: String,
        tiingoToken: String
    ): TiingoDataSource = TiingoDataSourceImpl(
        tiingoUrlBuilder = TiingoUrlBuilder(tiingoBaseUrl, tiingoToken),
        ktorClient = KtorModule.provideKtorClient()
    )
}
