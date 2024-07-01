package com.che.architecture.features.shared.app

import androidx.compose.runtime.Immutable
import com.che.architecture.base.mvi.DefaultStateStoreFactory
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
@Immutable
class AppMviViewModel @Inject constructor(
    private val mviStateStoreFactory: DefaultStateStoreFactory<AppMviState>,
    private val eventsListener: EventsListener<AppUiEvent>,
    private val processors: Set<@JvmSuppressWildcards IntentionProcessor<AppMviState, AppIntentions>>,
    private val intentionDispatcher: IntentionDispatcher<AppIntentions>
) : MviViewModel<AppMviState, AppIntentions, AppUiEvent> by DefaultViewModel(
    stateStore = mviStateStoreFactory.create(AppMviState()),
    eventsListener = eventsListener,
    intentionProcessors = processors,
    intentionDispatcher = intentionDispatcher,
    initialIntention = AppIntentions.InitialIntention
)
