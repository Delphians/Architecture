package com.che.architecture.features.homepage.mvi.processor

import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.utils.className
import com.che.architecture.features.homepage.di.homepageModule
import com.che.architecture.features.homepage.di.homepageModuleName
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.processor.OpenScreenDetailsProcessor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class OpenScreenDetailsProcessorTest {

    private val koinApp = startKoin {
        modules(homepageModule)
    }.koin

    private val eventListener = koinApp.get<EventsListener<HomepageUiEvent>>(homepageModuleName)

    private val testSubject = koinApp.get<IntentionProcessor<HomepageState, HomepageIntention>>(
        named(OpenScreenDetailsProcessor::class.className())
    )

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `When send OpenScreenDetails intention should get NavigateToDetails event`() = runTest {
        testSubject.process(
            flowOf(HomepageIntention.OpenScreenDetails(FakeStockData.fakeTicker))
        ).toList()

        assertTrue(eventListener.event.first()::class == HomepageUiEvent.NavigateToDetails::class)
    }

    @Test
    fun `When send OpenScreenDetails intention should have the ticker in the event`() = runTest {
        testSubject.process(
            flowOf(HomepageIntention.OpenScreenDetails(FakeStockData.fakeTicker))
        ).toList()

        val event = eventListener.event.first() as HomepageUiEvent.NavigateToDetails

        assertEquals(FakeStockData.fakeTicker, event.ticker)
    }
}
