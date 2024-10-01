package com.che.architecture.domain.prices.implementation

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.repositories.FakeStockPricesRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DailyTickerPricesImplTest {

    private val testSubject = DailyTickerPricesImpl(FakeStockPricesRepository())

    @Test
    fun `Usecase should return prices list`() = runTest {
        val count = FakeStockData.countOfDaysInRange(FakeStockData.dateRange)
        val result = testSubject(
            FakeStockData.fakeTicker,
            FakeStockData.dateRange
        )

        assertEquals(count, result.size)
    }
}
