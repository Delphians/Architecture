package com.che.architecture.data.remote.repositories

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.data.remote.tiingo.TiingoDataSource
import com.che.architecture.data.remote.utils.FakeData
import com.che.architecture.data.remote.utils.FakeData.fakeUrlBuilder
import com.che.architecture.data.remote.utils.createMockHttpClient
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.dateRange
import com.che.architecture.domain.fakes.FakeStockData.FAKE_TIINGO_URL
import com.che.architecture.domain.fakes.FakeStockData.FAKE_TOKEN
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Ticker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StockPricesRepositoryImplTest {

    private val defaultEventsHandler = DefaultEventsHandler<ErrorEvent>()

    private val testSubject = StockPricesRepositoryImpl(
        tiingoBaseUrl = FAKE_TIINGO_URL,
        tiingoToken = FAKE_TOKEN,
        errorDispatcher = defaultEventsHandler,
        tiingoDataSource = TiingoDataSource(
            tiingoBaseUrl = FAKE_TIINGO_URL,
            tiingoToken = FAKE_TOKEN,
            tiingoUrlBuilder = fakeUrlBuilder,
            ktorClient = createMockHttpClient(FakeData.mockEngine)
        )
    )

    @Test
    fun `Server should return two price objects`() = runTest {
        val count = FakeStockData.countOfDaysInRange(dateRange)
        val actualResult = testSubject.getDailyTickerPrices(FakeStockData.fakeTicker, dateRange)
        assertEquals(count, actualResult.size)
    }

    @Test
    fun `Server should return TiingoPriceError when the ticker is incorrect`() = runTest {
        testSubject.getDailyTickerPrices(Ticker(""), dateRange).firstOrNull()
        val actualResult = defaultEventsHandler.event.first()
        assertEquals(ErrorEvent.TiingoPriceError::class, actualResult::class)
    }
}
