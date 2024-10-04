package com.che.architecture.features.payments.di

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
import com.che.architecture.domain.di.errorDomainModule
import com.che.architecture.domain.di.useCaseModule
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.mvi.processor.EmptyIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.FailureIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.GetTickerPriceIntentionProcessor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun loadModules() {
    loadKoinModules(paymentsModule)
}

internal val paymentsModule = module {

    includes(useCaseModule, errorDomainModule)

    single<MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>> {
        DefaultViewModel(
            stateStore = get(),
            eventsListener = get(),
            intentionProcessors = get(),
            intentionDispatcher = get()
        )
    }

    single<EventsListener<PaymentsUiEvent>> {
        DefaultEventsHandler()
    }

    single<EventsDispatcher<PaymentsUiEvent>> {
        DefaultEventsHandler()
    }

    single<StateStore<PaymentsState>> {
        DefaultStateStore(PaymentsState())
    }

    single<IntentionDispatcher<PaymentsIntention>> {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<PaymentsState, PaymentsIntention>>> {
        setOf(
            GetTickerPriceIntentionProcessor(get()),
            FailureIntentionProcessor(),
            EmptyIntentionProcessor()
        )
    }
}
