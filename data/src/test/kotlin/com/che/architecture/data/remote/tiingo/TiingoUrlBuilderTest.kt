package com.che.architecture.data.remote.tiingo

import com.che.architecture.data.remote.tiingo.ProvidedNames.TIINGO_TOKEN
import com.che.architecture.domain.fakes.FakeStockData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class TiingoUrlBuilderTest {
    val fakeToken = "token"
    val fakeBaseUrl = "api.tiingo.com/tiingo"
    val dateRange = LocalDate.now().run { plusDays(2)..plusDays(5) }

    private val testSubject = TiingoUrlBuilder(fakeBaseUrl, fakeToken)

    @Test
    fun buildTiingoUrl() {
        val expectedUrl =
            "https://$fakeBaseUrl/${TiingoPathParams.DateTime.DAILY}" +
                    "/${FakeStockData.fakeTicker.value}/${TiingoPathParams.PRICES}" +
                    "?${TiingoPathParams.DateTime.START_DATE}=${dateRange.start}" +
                    "&${TiingoPathParams.DateTime.END_DATE}=${dateRange.endInclusive}" +
                    "&${TIINGO_TOKEN}=$fakeToken&${ProvidedNames.TIINGO_FORMAT}" +
                    "=${TiingoPathParams.Format.JSON}"

        val actualUrl = testSubject.apply {
            addPathParams(
                TiingoPathParams.DateTime.DAILY,
                FakeStockData.fakeTicker.value,
                TiingoPathParams.PRICES
            )
            addQueryParams(
                mapOf(
                    TiingoPathParams.DateTime.START_DATE to dateRange.start.toString(),
                    TiingoPathParams.DateTime.END_DATE to dateRange.endInclusive.toString()
                )
            )
        }.build()
        assertEquals(expectedUrl, actualUrl)
    }
}
