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
import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.domain.utils.className
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import com.che.architecture.features.shared.app.processor.TabChangedIntentionProcessor
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModuleName: StringQualifier by StringQualifierName(named("appModule"))

val appModule = module {

    includes(errorDomainModule)

    val handler = DefaultEventsHandler<AppUiEvent>()

    single<EventsListener<AppUiEvent>>(appModuleName) {
        handler
    }

    single<EventsDispatcher<AppUiEvent>>(appModuleName) {
        handler
    }

    single<StateStore<AppMviState>>(appModuleName) {
        DefaultStateStore(AppMviState())
    }

    single<IntentionDispatcher<AppIntentions>>(appModuleName) {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<AppMviState, AppIntentions>>>(appModuleName) {
        setOf(
            get(named(TabChangedIntentionProcessor::class.className()))
        )
    }

    single<IntentionProcessor<AppMviState, AppIntentions>>(named(TabChangedIntentionProcessor::class.className())) {
        TabChangedIntentionProcessor(handler)
    }

    single<MviViewModel<AppMviState, AppIntentions, AppUiEvent>>(appModuleName) {
        DefaultViewModel(
            stateStore = get(appModuleName),
            eventsListener = get(appModuleName),
            intentionProcessors = get<Set<IntentionProcessor<AppMviState, AppIntentions>>>(appModuleName),
            intentionDispatcher = get(appModuleName),
            initialIntention = AppIntentions.InitialIntention
        )
    }
}
