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
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun loadModules() {
    loadKoinModules(chartModule)
}

internal val chartModule = module {

    single<MviViewModel<ChartState, ChartIntention, ChartUiEvent>> {
        DefaultViewModel(
            stateStore = get(),
            eventsListener = get(),
            intentionProcessors = get(),
            intentionDispatcher = get()
        )
    }

    single<EventsListener<ChartUiEvent>> {
        DefaultEventsHandler()
    }

    single<EventsDispatcher<ChartUiEvent>> {
        DefaultEventsHandler()
    }

    single<StateStore<ChartState>> {
        DefaultStateStore(ChartState())
    }

    single<IntentionDispatcher<ChartIntention>> {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<ChartState, ChartIntention>>> {
        setOf(
            InitialIntentionProcessor()
        )
    }
}
