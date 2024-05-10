package com.che.architecture.data.di

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.data.remote.repositories.StockPricesRepositoryImpl
import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.repositories.StockPricesRepository

object RepositoriesModule {

    private var stockPricesRepository: StockPricesRepository? = null
    private val errorDispatcher: EventsDispatcher<ErrorEvent> =
        ErrorDomainModule.provideEventDispatcher()

    fun provideStockPricesRepository(
        tiingoBaseUrl: String,
        tiingoToken: String
    ): StockPricesRepository {
        if (stockPricesRepository == null) {
            stockPricesRepository =
                StockPricesRepositoryImpl(
                    tiingoBaseUrl,
                    tiingoToken,
                    errorDispatcher = errorDispatcher
                )
        }
        return stockPricesRepository as StockPricesRepository
    }
}
