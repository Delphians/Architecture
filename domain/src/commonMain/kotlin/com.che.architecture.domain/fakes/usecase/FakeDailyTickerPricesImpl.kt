package com.che.architecture.domain.fakes.usecase

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.prices.DailyTickerPrices
import kotlinx.datetime.LocalDate

class FakeDailyTickerPricesImpl : DailyTickerPrices {

    override suspend fun invoke(ticker: Ticker, dateRange: ClosedRange<LocalDate>): List<Price> {
        return FakeStockData.fakePricesGenerator(dateRange)
    }
}