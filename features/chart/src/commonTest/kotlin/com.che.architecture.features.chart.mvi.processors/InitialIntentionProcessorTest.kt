package com.che.architecture.features.chart.mvi.processors

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.utils.className
import com.che.architecture.features.chart.di.chartModule
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.EmptyResults
import com.che.architecture.features.chart.mvi.SetPointsResults
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class InitialIntentionProcessorTest {

    private val koinApp = startKoin {
        modules(chartModule)
    }.koin

    private val testSubject = koinApp.get<IntentionProcessor<ChartState, ChartIntention>>(
        named(InitialIntentionProcessor::class.className())
    )

    @AfterTest
    fun after() {
        stopKoin()
    }

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
