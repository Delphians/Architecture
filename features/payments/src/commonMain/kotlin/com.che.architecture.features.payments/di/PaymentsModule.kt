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
import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.domain.utils.className
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.mvi.processor.EmptyIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.FailureIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.GetTickerPriceIntentionProcessor
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val paymentsModuleName: StringQualifier
        by StringQualifierName(named("paymentsModule"))

internal val paymentsModule = module {

    includes(useCaseModule, errorDomainModule)

    val handler = DefaultEventsHandler<PaymentsUiEvent>()

    single<EventsListener<PaymentsUiEvent>>(paymentsModuleName) {
        handler
    }

    single<EventsDispatcher<PaymentsUiEvent>>(paymentsModuleName) {
        handler
    }

    single<StateStore<PaymentsState>>(paymentsModuleName) {
        DefaultStateStore(PaymentsState())
    }

    single<IntentionDispatcher<PaymentsIntention>>(paymentsModuleName) {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<PaymentsState, PaymentsIntention>>>(paymentsModuleName) {
        setOf(
            get(named(EmptyIntentionProcessor::class.className())),
            get(named(FailureIntentionProcessor::class.className())),
            get(named(GetTickerPriceIntentionProcessor::class.className()))
        )
    }

    single<IntentionProcessor<PaymentsState, PaymentsIntention>>(named(EmptyIntentionProcessor::class.className())) {
        EmptyIntentionProcessor()
    }

    single<IntentionProcessor<PaymentsState, PaymentsIntention>>(named(FailureIntentionProcessor::class.className())) {
        FailureIntentionProcessor()
    }

    single<IntentionProcessor<PaymentsState, PaymentsIntention>>(
        named(
            GetTickerPriceIntentionProcessor::class.className()
        )
    ) {
        GetTickerPriceIntentionProcessor(get())
    }

    single<MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent>>(paymentsModuleName) {
        DefaultViewModel(
            stateStore = get(paymentsModuleName),
            eventsListener = get(paymentsModuleName),
            intentionProcessors = get<Set<IntentionProcessor<PaymentsState, PaymentsIntention>>>(
                paymentsModuleName
            ),
            intentionDispatcher = get(paymentsModuleName)
        )
    }
}
