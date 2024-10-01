package com.che.architecture.data.remote.datasource.tiingo

import com.che.architecture.data.remote.datasource.ProvidedNames
import com.che.architecture.data.remote.datasource.ProvidedNames.TIINGO_TOKEN
import com.che.architecture.data.remote.datasource.TiingoPathParams
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.FAKE_TIINGO_URL
import com.che.architecture.domain.fakes.FakeStockData.FAKE_TOKEN
import com.che.architecture.domain.fakes.FakeStockData.dateRange
import kotlin.test.assertEquals
import kotlin.test.Test

internal class TiingoUrlBuilderTest {
    private val testSubject = TiingoUrlBuilder(FAKE_TIINGO_URL, FAKE_TOKEN)

    @Test
    fun buildTiingoUrl() {
        val expectedUrl =
            "https://$FAKE_TIINGO_URL/${TiingoPathParams.DateTime.DAILY}" +
                    "/${FakeStockData.fakeTicker.value}/${TiingoPathParams.PRICES}" +
                    "?${TiingoPathParams.DateTime.START_DATE}=${dateRange.start}" +
                    "&${TiingoPathParams.DateTime.END_DATE}=${dateRange.endInclusive}" +
                    "&${TIINGO_TOKEN}=$FAKE_TOKEN&${ProvidedNames.TIINGO_FORMAT}" +
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
