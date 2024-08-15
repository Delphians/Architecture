package com.che.architecture.features.shared.di

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
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor

object AppModule {

    fun getAppViewModel(): MviViewModel<AppMviState, AppIntentions, AppUiEvent> =
        DefaultViewModel(
            stateStore = getStateStore(),
            eventsListener = getEvenListener(),
            intentionProcessors = getProcessor(),
            intentionDispatcher = getIntentionDispatcher(),
            initialIntention = AppIntentions.InitialIntention
        )

    private val eventHandler = DefaultEventsHandler<AppUiEvent>()

    private fun getEventDispatcher(): EventsDispatcher<AppUiEvent> =
        eventHandler

    private fun getEvenListener(): EventsListener<AppUiEvent> =
        eventHandler

    private fun getIntentionDispatcher(): IntentionDispatcher<AppIntentions> =
        DefaultIntentionDispatcher()

    private fun getStateStore(): StateStore<AppMviState> =
        DefaultStateStore(AppMviState())

    private fun getProcessor(): Set<IntentionProcessor<AppMviState, AppIntentions>> =
        setOf(TabChangedIntentionProcessor(getEventDispatcher()))
}
