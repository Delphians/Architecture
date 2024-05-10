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
import com.che.architecture.domain.di.UseCaseModule
import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.usecase.prices.DailyTickerPricesUseCase
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.PaymentsUiEvent
import com.che.architecture.features.payments.mvi.processor.EmptyIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.FailureIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.GetTickerPriceIntentionProcessor

object PaymentsModule {

    private lateinit var dailyTickerPricesUseCase: DailyTickerPricesUseCase

    fun paymentsModuleInjection(
        stockPricesRepository: StockPricesRepository
    ) {
        dailyTickerPricesUseCase =
            UseCaseModule.provideDailyTickerPricesUseCase(stockPricesRepository)
    }

    internal fun getViewModel(): MviViewModel<PaymentsState, PaymentsIntention, PaymentsUiEvent> =
        DefaultViewModel(
            stateStore = getStateStore(),
            eventsListener = getEventListener(),
            intentionProcessors = getProcessor(dailyTickerPricesUseCase),
            intentionDispatcher = getIntentionDispatcher()
        )

    private val eventHandler = DefaultEventsHandler<PaymentsUiEvent>()

    private fun getEventListener(): EventsListener<PaymentsUiEvent> =
        eventHandler

    private fun getEventDispatcher(): EventsDispatcher<PaymentsUiEvent> =
        eventHandler

    private fun getStateStore(): StateStore<PaymentsState> =
        DefaultStateStore(PaymentsState())

    private fun getIntentionDispatcher(): IntentionDispatcher<PaymentsIntention> =
        DefaultIntentionDispatcher()

    private fun getProcessor(
        dailyTickerPricesUseCase: DailyTickerPricesUseCase
    ): Set<IntentionProcessor<PaymentsState, PaymentsIntention>> =
        setOf(
            GetTickerPriceIntentionProcessor(dailyTickerPricesUseCase),
            FailureIntentionProcessor(),
            EmptyIntentionProcessor()
        )
}
