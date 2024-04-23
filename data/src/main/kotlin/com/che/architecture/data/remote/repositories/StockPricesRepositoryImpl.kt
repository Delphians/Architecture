package com.che.architecture.data.remote.repositories

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.data.remote.mapBoth
import com.che.architecture.data.remote.tiingo.TiingoDataSource
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.repositories.StockPricesRepository
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class StockPricesRepositoryImpl @Inject constructor(
    private val tiingoDataSource: TiingoDataSource,
    private val errorDispatcher: EventsDispatcher<ErrorEvent>
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
