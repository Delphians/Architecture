package com.che.architecture.features.payments.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.mvi.PaymentsViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class PaymentsModule {

    @Binds
    internal abstract fun bindsMviViewModel(
        it: PaymentsViewModel
    ): MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>

    @Binds
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<PaymentsUiEvent>
    ): EventsListener<PaymentsUiEvent>

    @Binds
    internal abstract fun bindsEventDispatcher(
        handler: DefaultEventsHandler<PaymentsUiEvent>
    ): EventsDispatcher<PaymentsUiEvent>

    @Binds
    internal abstract fun bindsIntentionDispatcher(
        dispatcher: DefaultIntentionDispatcher<PaymentsIntention>
    ): IntentionDispatcher<PaymentsIntention>
}
