package com.che.architecture.features.payments.mvi

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
internal class PaymentsViewModel @Inject constructor(
    private val mviStateStoreFactory: DefaultStateStoreFactory<PaymentsState>,
    private val eventsListener: EventsListener<PaymentsUiEvent>,
    private val processors: Set<@JvmSuppressWildcards IntentionProcessor<PaymentsState, PaymentsIntention>>,
    private val intentionDispatcher: IntentionDispatcher<PaymentsIntention>
) : MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent> by DefaultViewModel(
    stateStore = mviStateStoreFactory.create(PaymentsState()),
    eventsListener = eventsListener,
    intentionProcessors = processors,
    intentionDispatcher = intentionDispatcher
)
