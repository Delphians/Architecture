package com.che.architecture.features.chart.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.DefaultStateStore
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.StateStore
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.mvi.processors.InitialIntentionProcessor

internal object ChartModule {
    fun getEventListener(): EventsListener<ChartUiEvent> =
        DefaultEventsHandler()

    fun getIntentionDispatcher(): IntentionDispatcher<ChartIntention> =
        DefaultIntentionDispatcher()

    fun getStateStore(): StateStore<ChartState> =
        DefaultStateStore(ChartState())

    fun getChartProcessors(): Set<IntentionProcessor<ChartState, ChartIntention>> =
        setOf(InitialIntentionProcessor())
}
