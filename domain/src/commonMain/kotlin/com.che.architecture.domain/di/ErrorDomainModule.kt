package com.che.architecture.domain.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent
import org.koin.dsl.module

val errorDomainModule = module {
    single<EventsListener<ErrorEvent>> {
        DefaultEventsHandler()
    }

    single<EventsDispatcher<ErrorEvent>> {
        DefaultEventsHandler()
    }
}
