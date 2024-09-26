package com.che.architecture.features.chart.mvi.processors

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.EmptyResults
import com.che.architecture.features.chart.mvi.SetPointsResults
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class InitialIntentionProcessorTest {
    private val testSubject = InitialIntentionProcessor()

    @Test
    fun `When send InitialIntention and the points should get SetPointsResults`() = runTest {
        val result = testSubject.process(
            flowOf(ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints))
        ).first()

        assertTrue(SetPointsResults::class == result::class)
    }

    @Test
    fun `When send InitialIntention without points should get EmptyResults`() = runTest {
        val result = testSubject.process(
            flowOf(ChartIntention.InitialIntention(persistentListOf()))
        ).first()

        assertTrue(EmptyResults::class == result::class)
    }
}
