package com.che.architecture.data.remote.tiingo

import com.che.architecture.data.remote.Failure
import com.che.architecture.data.remote.Success
import com.che.architecture.data.remote.utils.FakeData.engine
import com.che.architecture.data.remote.utils.FakeData.fakeUrlBuilder
import com.che.architecture.data.remote.utils.createMockHttpClient
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.dateRange
import com.che.architecture.domain.model.Ticker
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

internal class TiingoDataSourceTest {

    private val testSubject = TiingoDataSource(createMockHttpClient(engine), fakeUrlBuilder)

    @Test
    fun `getDailyTickerPriceData method should return Success response`() =
        runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
            val response = testSubject.getDailyTickerPriceData(FakeStockData.fakeTicker, dateRange)
            assertTrue(response::class == Success::class)
        }

    @Test
    fun `getDailyTickerPriceData method should return list of prices`() =
        runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
            val count = FakeStockData.countOfDaysInRange(dateRange)
            val response = testSubject.getDailyTickerPriceData(FakeStockData.fakeTicker, dateRange)
            assertEquals(count, response.component1()?.size)
        }

    @Test
    fun `getDailyTickerPriceData method should return Failure response when ticker is empty`() =
        runTest(context = StandardTestDispatcher(), timeout = 20.seconds) {
            val response = testSubject.getDailyTickerPriceData(Ticker(""), dateRange)
            assertTrue(response::class == Failure::class)
        }
}
