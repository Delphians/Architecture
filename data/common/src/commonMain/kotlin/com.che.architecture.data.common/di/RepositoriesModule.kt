package com.che.architecture.data.common.di

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.data.common.repositories.StockPricesRepositoryImpl
import com.che.architecture.data.remote.datasource.di.RemoteDataSourceModule
import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.repositories.StockPricesRepository

object RepositoriesModule {

    private val errorDispatcher: EventsDispatcher<ErrorEvent> =
        ErrorDomainModule.provideEventDispatcher()

    fun provideStockPricesRepository(
        tiingoBaseUrl: String,
        tiingoToken: String
    ): StockPricesRepository {
        return StockPricesRepositoryImpl(
            errorDispatcher = errorDispatcher,
            tiingoDataSource = RemoteDataSourceModule.provideTiingoDataSource(
                tiingoBaseUrl,
                tiingoToken
            )
        )
    }
}
