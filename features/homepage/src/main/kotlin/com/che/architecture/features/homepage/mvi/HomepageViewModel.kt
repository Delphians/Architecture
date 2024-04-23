package com.che.architecture.features.homepage.mvi

import com.che.architecture.base.mvi.DefaultStateStoreFactory
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import javax.inject.Inject

internal class HomepageViewModel @Inject constructor(
    private val mviStateStoreFactory: DefaultStateStoreFactory<HomepageState>,
    private val eventsListener: EventsListener<HomepageUiEvent>,
    private val processors: Set<@JvmSuppressWildcards IntentionProcessor<HomepageState, HomepageIntention>>,
    private val intentionDispatcher: IntentionDispatcher<HomepageIntention>
) : MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent> by DefaultViewModel(
    stateStore = mviStateStoreFactory.create(HomepageState()),
    eventsListener = eventsListener,
    intentionProcessors = processors,
    intentionDispatcher = intentionDispatcher
)
