package com.che.architecture.domain.usecase.prices.implementation

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.usecase.prices.DailyTickerPrices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

internal class DailyTickerPricesImpl @Inject constructor(
    private val stockPricesRepository: StockPricesRepository
) : DailyTickerPrices {

    override suspend fun invoke(ticker: Ticker, dateRange: ClosedRange<LocalDate>): List<Price> =
        withContext(Dispatchers.IO) {
            stockPricesRepository.getDailyTickerPrices(ticker, dateRange)
        }
}
