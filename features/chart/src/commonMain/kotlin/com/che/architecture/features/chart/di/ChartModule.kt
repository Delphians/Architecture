package com.che.architecture.features.chart.di

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
import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.domain.utils.className
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.mvi.processors.InitialIntentionProcessor
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val chartModuleName: StringQualifier by StringQualifierName(named("chartModule"))

internal val chartModule = module {

    val handler = DefaultEventsHandler<ChartUiEvent>()

    single<EventsListener<ChartUiEvent>>(chartModuleName) {
        handler
    }

    single<EventsDispatcher<ChartUiEvent>>(chartModuleName) {
        handler
    }

    single<StateStore<ChartState>>(chartModuleName) {
        DefaultStateStore(ChartState())
    }

    single<IntentionDispatcher<ChartIntention>>(chartModuleName) {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<ChartState, ChartIntention>>>(chartModuleName) {
        setOf(
            get(named(InitialIntentionProcessor::class.className()))
        )
    }

    single<IntentionProcessor<ChartState, ChartIntention>>(named(InitialIntentionProcessor::class.className())) {
        InitialIntentionProcessor()
    }

    single<MviViewModel<ChartState, ChartIntention, ChartUiEvent>>(chartModuleName) {
        DefaultViewModel(
            stateStore = get(chartModuleName),
            eventsListener = get(chartModuleName),
            intentionProcessors = get<Set<IntentionProcessor<ChartState, ChartIntention>>>(
                chartModuleName
            ),
            intentionDispatcher = get(chartModuleName)
        )
    }
}
