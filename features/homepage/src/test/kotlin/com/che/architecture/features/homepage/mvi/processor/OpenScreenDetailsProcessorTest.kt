package com.che.architecture.features.homepage.mvi.processor

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.homepage.mvi.HomepageIntention
import com.che.architecture.features.homepage.mvi.HomepageUiEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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
