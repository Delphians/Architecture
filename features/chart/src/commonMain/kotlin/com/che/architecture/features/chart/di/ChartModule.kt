package com.che.architecture.features.chart.di

import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.mvi.processors.InitialIntentionProcessor
import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.DefaultStateStore
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.base.mvi.interfaces.StateStore

internal object ChartModule {

    fun getViewModel(): MviViewModel<ChartState, ChartIntention, ChartUiEvent> =
        DefaultViewModel(
            stateStore = getStateStore(),
            eventsListener = getEventListener(),
            intentionProcessors = getProcessors(),
            intentionDispatcher = getIntentionDispatcher()
        )

    private val eventHandler = DefaultEventsHandler<ChartUiEvent>()

    private fun getEventListener(): EventsListener<ChartUiEvent> =
        eventHandler

    private fun getEventDispatcher(): EventsDispatcher<ChartUiEvent> =
        eventHandler

    private fun getIntentionDispatcher(): IntentionDispatcher<ChartIntention> =
        DefaultIntentionDispatcher()

    private fun getStateStore(): StateStore<ChartState> =
        DefaultStateStore(ChartState())

    private fun getProcessors(): Set<IntentionProcessor<ChartState, ChartIntention>> =
        setOf(InitialIntentionProcessor())
}
