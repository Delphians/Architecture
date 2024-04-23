package com.che.architecture.domain.repositories

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import java.time.LocalDate

interface StockPricesRepository {
    suspend fun getDailyTickerPrices(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): List<Price>
}
