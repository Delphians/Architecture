package com.che.architecture.domain.fakes.repositories

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.repositories.StockPricesRepository
import kotlinx.datetime.LocalDate

internal class FakeStockPricesRepository : StockPricesRepository {
    override suspend fun getDailyTickerPrices(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): List<Price> = FakeStockData.fakePricesGenerator(dateRange)
}
