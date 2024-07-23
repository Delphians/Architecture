package com.che.architecture.features.chart.mvi

import androidx.compose.runtime.Immutable
import com.che.architecture.base.mvi.DefaultStateStoreFactory
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import javax.inject.Inject

@Immutable
internal class ChartViewModel @Inject constructor(
    private val mviStateStoreFactory: DefaultStateStoreFactory<ChartState>,
    private val eventsListener: EventsListener<ChartUiEvent>,
    private val processors: Set<@JvmSuppressWildcards IntentionProcessor<ChartState, ChartIntention>>,
    private val intentionDispatcher: IntentionDispatcher<ChartIntention>
) : MviViewModel<ChartState, ChartIntention, ChartUiEvent> by DefaultViewModel(
    stateStore = mviStateStoreFactory.create(ChartState()),
    eventsListener = eventsListener,
    intentionProcessors = processors,
    intentionDispatcher = intentionDispatcher
)
