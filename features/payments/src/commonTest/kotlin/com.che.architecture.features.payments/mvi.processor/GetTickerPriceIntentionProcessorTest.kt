package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.usecase.FakeDailyTickerPricesImpl
import com.che.architecture.domain.prices.DailyTickerPrices
import com.che.architecture.features.payments.mvi.DailyPriceResults
import com.che.architecture.features.payments.mvi.LoadingResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class GetTickerPriceIntentionProcessorTest {

    private val dailyTickerPrices: DailyTickerPrices = FakeDailyTickerPricesImpl()
    private val testSubject = GetTickerPriceIntentionProcessor(dailyTickerPrices)

    @Test
    fun `When send GetTickerPriceIntention should get LoadingResults at first`() = runTest {
        val actualResult = testSubject.process(
            flowOf(
                PaymentsIntention.GetTickerPriceIntention(
                    FakeStockData.fakeTicker,
                    FakeStockData.dateRange
                )
            )
        ).first()

        assertTrue(actualResult::class == LoadingResults::class)
    }

    @Test
    fun `When send GetTickerPriceIntention should get DailyPriceResults`() = runTest {

        val results = testSubject.process(
            flowOf(
                PaymentsIntention.GetTickerPriceIntention(
                    FakeStockData.fakeTicker,
                    FakeStockData.dateRange
                )
            )
        ).drop(1).toList()

        assertTrue(results[0]::class == DailyPriceResults::class)
    }
}
