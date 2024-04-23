package com.che.architecture.features.shared.app

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.emptyResult
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor
import com.che.architecture.ui.compose.tabs.BottomTab
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TabChangedIntentionProcessorTest {

    private val defaultEventsHandler = DefaultEventsHandler<AppUiEvent>()
    private val testSubject = TabChangedIntentionProcessor(defaultEventsHandler)

    @Test
    fun `Processor should emit TabChanged event when send TabChangedIntention`() = runTest {
        val tab = BottomTab.PAYMENTS
        testSubject.process(flowOf(AppIntentions.TabChangedIntention(tab)))
            .firstOrNull()
        assertEquals(AppUiEvent.TabChanged(tab), defaultEventsHandler.event.first())
    }

    @Test
    fun `Processor shouldn't update the state, get the empty result`() = runTest {
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
