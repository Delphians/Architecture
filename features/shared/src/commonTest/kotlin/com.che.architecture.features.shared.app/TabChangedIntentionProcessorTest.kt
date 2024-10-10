package com.che.architecture.features.shared.app

import com.che.architecture.atomic.design.tabs.BottomTab
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.emptyResult
import com.che.architecture.domain.utils.className
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor
import com.che.architecture.features.shared.di.appModule
import com.che.architecture.features.shared.di.appModuleName
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TabChangedIntentionProcessorTest {

    private val koinApp = startKoin {
        modules(appModule)
    }.koin

    private val defaultEventsListener = koinApp.get<EventsListener<AppUiEvent>>(appModuleName)

    private val testSubject = koinApp.get<IntentionProcessor<AppMviState, AppIntentions>>(
        named(TabChangedIntentionProcessor::class.className())
    )

    @AfterTest
    fun after() {
        stopKoin()
    }

    @Test
    fun `Processor should emit TabChanged event when send TabChangedIntention`() = runTest {
        val tab = BottomTab.PAYMENTS
        testSubject.process(flowOf(AppIntentions.TabChangedIntention(tab)))
            .firstOrNull()
        assertEquals(AppUiEvent.TabChanged(tab), defaultEventsListener.event.first())
    }

    @Test
    fun `Processor shouldn't update the state get the empty result`() = runTest {
        val result =
            testSubject.process(flowOf(AppIntentions.TabChangedIntention(BottomTab.PAYMENTS)))
                .firstOrNull()
        assertEquals(emptyResult<AppMviState>(), result)
    }

    @Test
    fun `Processor shouldn't handle InitialIntention`() = runTest {
        val result =
            testSubject.process(flowOf(AppIntentions.InitialIntention))
                .firstOrNull()
        assertEquals(null, result)
    }
}
