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
import com.che.architecture.domain.di.errorDomainModule
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor
import org.koin.dsl.module

val appModule = module {

    includes(errorDomainModule)

    single<MviViewModel<AppMviState, AppIntentions, AppUiEvent>> {
        DefaultViewModel(
            stateStore = get(),
            eventsListener = get(),
            intentionProcessors = get(),
            intentionDispatcher = get(),
            initialIntention = AppIntentions.InitialIntention
        )
    }

    single<EventsListener<AppUiEvent>> {
        DefaultEventsHandler()
    }

    single<EventsDispatcher<AppUiEvent>> {
        DefaultEventsHandler()
    }

    single<StateStore<AppMviState>> {
        DefaultStateStore(AppMviState())
    }

    single<IntentionDispatcher<AppIntentions>> {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<AppMviState, AppIntentions>>> {
        setOf(
            TabChangedIntentionProcessor(get())
        )
    }
}
