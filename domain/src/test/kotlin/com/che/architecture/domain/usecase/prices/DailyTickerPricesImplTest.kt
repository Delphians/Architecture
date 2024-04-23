package com.che.architecture.domain.usecase.prices

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.repositories.FakeStockPricesRepository
import com.che.architecture.domain.usecase.prices.implementation.DailyTickerPricesImpl
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
