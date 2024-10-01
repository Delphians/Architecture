package com.che.architecture.data.remote.datasource.tiingo

import com.che.architecture.data.remote.datasource.TiingoPathParams.DateTime.DAILY
import com.che.architecture.data.remote.datasource.TiingoPathParams.DateTime.END_DATE
import com.che.architecture.data.remote.datasource.TiingoPathParams.DateTime.START_DATE
import com.che.architecture.data.remote.datasource.TiingoPathParams.PRICES
import com.che.architecture.data.remote.datasource.call.getSafeQuery
import com.che.architecture.domain.model.Failure
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Result
import com.che.architecture.domain.model.Ticker
import io.ktor.client.HttpClient
import kotlinx.datetime.LocalDate

internal class TiingoDataSourceImpl(
    private val tiingoUrlBuilder: TiingoUrlBuilder,
    private val ktorClient: HttpClient
) : TiingoDataSource {

    override suspend fun getDailyTickerPriceData(
        ticker: Ticker,
        dateRange: ClosedRange<LocalDate>
    ): Result<List<Price>, Throwable> =
        if (ticker.value.isNotBlank() && dateRange.start < dateRange.endInclusive) {
            ktorClient.getSafeQuery<List<Price>>(
                tiingoUrlBuilder.apply {
                    addPathParams(DAILY, ticker.value, PRICES)
                    addQueryParams(
                        mapOf(
                            START_DATE to dateRange.start.toString(),
                            END_DATE to dateRange.endInclusive.toString()
                        )
                    )
                }.build()
            )
        } else {
            Failure(Throwable())
        }
}
