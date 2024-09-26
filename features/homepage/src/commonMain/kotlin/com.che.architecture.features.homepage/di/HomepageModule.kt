package com.che.architecture.features.homepage.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.DefaultStateStore
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.StateStore
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.processor.OpenScreenDetailsProcessor

internal object HomepageModule {

    fun getViewModel(): MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent> =
        DefaultViewModel(
            stateStore = getStateStore(),
            eventsListener = getEventListener(),
            intentionProcessors = getProcessor(),
            intentionDispatcher = getIntentionDispatcher()
        )

    private val eventHandler = DefaultEventsHandler<HomepageUiEvent>()

    private fun getEventListener(): EventsListener<HomepageUiEvent> =
        eventHandler

    private fun getEventDispatcher(): EventsDispatcher<HomepageUiEvent> =
        eventHandler

    private fun getStateStore(): StateStore<HomepageState> =
        DefaultStateStore(HomepageState())

    private fun getIntentionDispatcher(): IntentionDispatcher<HomepageIntention> =
        DefaultIntentionDispatcher()

    private fun getProcessor(): Set<IntentionProcessor<HomepageState, HomepageIntention>> =
        setOf(OpenScreenDetailsProcessor(getEventDispatcher()))
}
