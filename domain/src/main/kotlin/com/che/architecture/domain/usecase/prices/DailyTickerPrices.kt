package com.che.architecture.domain.usecase.prices

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import java.time.LocalDate

interface DailyTickerPrices {
    suspend operator fun invoke(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): List<Price>
}
