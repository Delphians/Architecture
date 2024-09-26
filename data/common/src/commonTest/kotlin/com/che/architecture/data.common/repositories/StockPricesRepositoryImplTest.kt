package com.che.architecture.data.common.repositories

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.data.remote.datasource.fakes.FakesTiingoDataSource
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.dateRange
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Ticker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StockPricesRepositoryImplTest {

    private val defaultEventsHandler = DefaultEventsHandler<ErrorEvent>()

    private val tiingoDataSourceImpl = FakesTiingoDataSource()

    private val testSubject = StockPricesRepositoryImpl(
        errorDispatcher = defaultEventsHandler,
        tiingoDataSource = tiingoDataSourceImpl,
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
