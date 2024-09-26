package com.che.architecture.features.homepage.mvi.processor

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.processor.OpenScreenDetailsProcessor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class OpenScreenDetailsProcessorTest {

    private val eventDispatcher = DefaultEventsHandler<HomepageUiEvent>()
    private val testSubject = OpenScreenDetailsProcessor(eventDispatcher)

    @Test
    fun `When send OpenScreenDetails intention should get NavigateToDetails event`() = runTest {
        testSubject.process(
            flowOf(HomepageIntention.OpenScreenDetails(FakeStockData.fakeTicker))
        ).toList()

        assertTrue(eventDispatcher.event.first()::class == HomepageUiEvent.NavigateToDetails::class)
    }

    @Test
    fun `When send OpenScreenDetails intention should have the ticker in the event`() = runTest {
        testSubject.process(
            flowOf(HomepageIntention.OpenScreenDetails(FakeStockData.fakeTicker))
        ).toList()

        val event = eventDispatcher.event.first() as HomepageUiEvent.NavigateToDetails

        assertEquals(FakeStockData.fakeTicker, event.ticker)
    }
}
