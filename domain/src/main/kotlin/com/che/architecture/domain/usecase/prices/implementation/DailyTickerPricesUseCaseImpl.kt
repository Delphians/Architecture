package com.che.architecture.domain.usecase.prices.implementation

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.usecase.prices.DailyTickerPricesUseCase
import java.time.LocalDate

internal class DailyTickerPricesUseCaseImpl(
    private val stockPricesRepository: StockPricesRepository
) : DailyTickerPricesUseCase {

    override suspend fun invoke(ticker: Ticker, dateRange: ClosedRange<LocalDate>): List<Price> =
        stockPricesRepository.getDailyTickerPrices(ticker, dateRange)
}
