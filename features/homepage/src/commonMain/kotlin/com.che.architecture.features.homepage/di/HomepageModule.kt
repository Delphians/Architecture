package com.che.architecture.features.homepage.di

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
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.processor.OpenScreenDetailsProcessor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal fun loadModules() {
    loadKoinModules(homepageModule)
}

internal val homepageModule = module {

    includes(errorDomainModule)

    single<MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>> {
        DefaultViewModel(
            stateStore = get(),
            eventsListener = get(),
            intentionProcessors = get(),
            intentionDispatcher = get()
        )
    }

    single<EventsListener<HomepageUiEvent>> {
        DefaultEventsHandler()
    }

    single<EventsDispatcher<HomepageUiEvent>> {
        DefaultEventsHandler()
    }

    single<StateStore<HomepageState>> {
        DefaultStateStore(HomepageState())
    }

    single<IntentionDispatcher<HomepageIntention>> {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<HomepageState, HomepageIntention>>> {
        setOf(
            OpenScreenDetailsProcessor(get())
        )
    }
}
