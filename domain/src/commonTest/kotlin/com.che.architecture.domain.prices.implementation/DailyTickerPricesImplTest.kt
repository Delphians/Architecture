package com.che.architecture.domain.prices.implementation

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.repositories.FakeStockPricesRepository
import com.che.architecture.domain.prices.DailyTickerPrices
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DailyTickerPricesImplTest {

    private val koinApp = startKoin {
        modules(
            module {
            factory<DailyTickerPrices> {
                DailyTickerPricesImpl(FakeStockPricesRepository())
            }
        }
        )
    }.koin

    private val testSubject = koinApp.get<DailyTickerPrices>()

    @AfterTest
    fun after() {
        stopKoin()
    }

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
