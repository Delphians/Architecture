package com.che.architecture.data.remote.datasource.tiingo

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.model.Result
import kotlinx.datetime.LocalDate

interface TiingoDataSource {
    suspend fun getDailyTickerPriceData(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): Result<List<Price>, Throwable>
}
