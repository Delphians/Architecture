package com.che.architecture.domain.repositories

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import kotlinx.datetime.LocalDate

interface StockPricesRepository {
    suspend fun getDailyTickerPrices(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): List<Price>
}
