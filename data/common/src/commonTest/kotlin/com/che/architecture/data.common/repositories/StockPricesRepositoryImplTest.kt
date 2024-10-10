package com.che.architecture.data.common.repositories

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.data.remote.datasource.fakes.FakesTiingoDataSource
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.dateRange
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.model.Ticker
import com.che.architecture.domain.repositories.StockPricesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StockPricesRepositoryImplTest {

    private val eventListener = DefaultEventsHandler<ErrorEvent>()

    private val tiingoDataSourceImpl = FakesTiingoDataSource()

    private val koinApp = startKoin {
        modules(
            module {
            single<StockPricesRepository> {
                StockPricesRepositoryImpl(
                    errorDispatcher = eventListener,
                    tiingoDataSource = tiingoDataSourceImpl,
                )
            }
        }
        )
    }.koin

    private val testSubject = koinApp.get<StockPricesRepository>()

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Server should return two price objects`() = runTest {
        val count = FakeStockData.countOfDaysInRange(dateRange)
        val actualResult = testSubject.getDailyTickerPrices(FakeStockData.fakeTicker, dateRange)
        assertEquals(count, actualResult.size)
    }

    @Test
    fun `Server should return TiingoPriceError when the ticker is incorrect`() = runTest {
        testSubject.getDailyTickerPrices(Ticker(""), dateRange).firstOrNull()
        val actualResult = eventListener.event.first()
        assertEquals(ErrorEvent.TiingoPriceError::class, actualResult::class)
    }
}
