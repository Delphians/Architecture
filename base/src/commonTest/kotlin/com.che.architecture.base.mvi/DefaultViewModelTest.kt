package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DefaultViewModelTest {

    private val defaultEventsHandler = DefaultEventsHandler<TestEvent>()

    private val intentionProcessors: Set<IntentionProcessor<TestState, TestIntention>> =
        setOf(
            createProcessor {
                it.filterIsInstance<TestIntention.PlusIntention>()
                    .onEach {
                        defaultEventsHandler.dispatch(TestEvent.Plus(1))
                    }.map {
                        PlusTestMviResult(it.value)
                    }
            },
            createProcessor {
                it.filterIsInstance<TestIntention.MinusIntention>()
                    .onEach {
                        defaultEventsHandler.dispatch(TestEvent.Minus(1))
                    }.map {
                        MinusTestMviResult(it.value)
                    }
            }
        )

    private val testSubject = DefaultViewModel(
        stateStore = DefaultStateStore(TestState(0)),
        eventsListener = defaultEventsHandler,
        intentionProcessors = intentionProcessors,
        intentionDispatcher = DefaultIntentionDispatcher()
    )

    @Test
    fun `State should be changed when the minus intention was send`() = runTest {
        val scope = CoroutineScope(Dispatchers.Unconfined)
        testSubject.apply {
            start(scope)
            dispatchIntention(TestIntention.MinusIntention(1))
            state.onEach {
                assertEquals(-1, it.finalCount)
            }.launchIn(scope)
            scope.cancel()
        }
    }

    @Test
    fun `Plus intention should dispatch Plus event`() = runTest {
        val scope = CoroutineScope(Dispatchers.Unconfined)
        testSubject.apply {
            start(scope)
            dispatchIntention(TestIntention.PlusIntention(1))
            assertEquals(TestEvent.Plus(1), event.first())
            scope.cancel()
        }
    }
}
