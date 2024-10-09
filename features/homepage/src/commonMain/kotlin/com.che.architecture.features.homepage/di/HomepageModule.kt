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
import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.domain.utils.className
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.mvi.homeScreen.processor.OpenScreenDetailsProcessor
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val homepageModuleName: StringQualifier
        by StringQualifierName(named("homepageModule"))

internal val homepageModule = module {

    includes(errorDomainModule)

    val handler = DefaultEventsHandler<HomepageUiEvent>()

    single<EventsListener<HomepageUiEvent>>(homepageModuleName) {
        handler
    }

    single<EventsDispatcher<HomepageUiEvent>>(homepageModuleName) {
        handler
    }

    single<StateStore<HomepageState>>(homepageModuleName) {
        DefaultStateStore(HomepageState())
    }

    single<IntentionDispatcher<HomepageIntention>>(homepageModuleName) {
        DefaultIntentionDispatcher()
    }

    single<Set<IntentionProcessor<HomepageState, HomepageIntention>>>(homepageModuleName) {
        setOf(
            get(named(OpenScreenDetailsProcessor::class.className()))
        )
    }

    single<IntentionProcessor<HomepageState, HomepageIntention>>(named(OpenScreenDetailsProcessor::class.className())) {
        OpenScreenDetailsProcessor(get(homepageModuleName))
    }

    single<MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>>(homepageModuleName) {
        DefaultViewModel(
            stateStore = get(homepageModuleName),
            eventsListener = get(homepageModuleName),
            intentionProcessors = get<Set<IntentionProcessor<HomepageState, HomepageIntention>>>(
                homepageModuleName
            ),
            intentionDispatcher = get(homepageModuleName)
        )
    }
}
