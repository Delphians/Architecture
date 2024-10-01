package com.che.architecture.data.common.repositories

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSource
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.model.mapBoth
import com.che.architecture.domain.repositories.StockPricesRepository
import kotlinx.datetime.LocalDate

internal class StockPricesRepositoryImpl(
    private val errorDispatcher: EventsDispatcher<ErrorEvent>,
    private val tiingoDataSource: TiingoDataSource
) : StockPricesRepository {

    override suspend fun getDailyTickerPrices(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): List<Price> {
        return if (ticker.value.isBlank() || dateRange.isEmpty()) {
            error(ticker, dateRange)
        } else {
            tiingoDataSource.getDailyTickerPriceData(ticker, dateRange).mapBoth(
                success = {
                    it
                },
                failure = {
                    error(ticker, dateRange, it)
                }
            )
        }
    }

    private fun error(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>,
        cause: Throwable? = null
    ): List<Price> {
        errorDispatcher.dispatch(ErrorEvent.TiingoPriceError(ticker, dateRange, cause))
        return emptyList()
    }
}
