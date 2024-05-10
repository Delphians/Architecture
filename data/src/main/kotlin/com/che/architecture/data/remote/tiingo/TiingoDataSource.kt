package com.che.architecture.data.remote.tiingo

import com.che.architecture.data.di.KtorModule.provideKtorClient
import com.che.architecture.data.remote.Failure
import com.che.architecture.data.remote.Result
import com.che.architecture.data.remote.getSafeQuery
import com.che.architecture.data.remote.tiingo.TiingoPathParams.DateTime.DAILY
import com.che.architecture.data.remote.tiingo.TiingoPathParams.DateTime.END_DATE
import com.che.architecture.data.remote.tiingo.TiingoPathParams.DateTime.START_DATE
import com.che.architecture.data.remote.tiingo.TiingoPathParams.PRICES
import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import io.ktor.client.HttpClient
import kotlinx.datetime.LocalDate

internal class TiingoDataSource(
    private val tiingoBaseUrl: String,
    private val tiingoToken: String,
    private val tiingoUrlBuilder: TiingoUrlBuilder = TiingoUrlBuilder(tiingoBaseUrl, tiingoToken),
    private val ktorClient: HttpClient = provideKtorClient()
) {

    suspend fun getDailyTickerPriceData(
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
