package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.usecase.prices.DailyTickerPrices
import com.che.architecture.features.payments.mvi.DailyPriceResults
import com.che.architecture.features.payments.mvi.LoadingResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class InitialIntentionProcessorTest {

    private val dailyTickerPrices: DailyTickerPrices = mockk()
    private val testSubject = InitialIntentionProcessor(dailyTickerPrices)

    @Test
    fun `When send InitialIntention should get LoadingResults at first`() = runTest {
        val actualResult = testSubject.process(
            flowOf(
                PaymentsIntention.InitialIntention(
                    FakeStockData.fakeTicker,
                    FakeStockData.dateRange
                )
            )
        ).first()

        assertTrue(actualResult::class == LoadingResults::class)
    }

    @Test
    fun `When send InitialIntention should get DailyPriceResults`() = runTest {
        val dateRange = FakeStockData.fakeStartDate..FakeStockData.fakeEndDate
        val prices = FakeStockData.fakePricesGenerator(dateRange)

        coEvery {
            dailyTickerPrices(
                FakeStockData.fakeTicker, dateRange
            )
        } returns prices

        val results = testSubject.process(
            flowOf(
                PaymentsIntention.InitialIntention(
                    FakeStockData.fakeTicker,
                    FakeStockData.dateRange
                )
            )
        ).drop(1).toList()

        assertTrue(results[0]::class == DailyPriceResults::class)
    }
}
