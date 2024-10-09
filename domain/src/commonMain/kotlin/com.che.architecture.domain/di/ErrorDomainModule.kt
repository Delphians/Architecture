package com.che.architecture.domain.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent
import com.che.architecture.domain.utils.StringQualifierName
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val errorDomainModuleName: StringQualifier by StringQualifierName(named("errorDomainModule"))

val errorDomainModule = module {

    val handler = DefaultEventsHandler<ErrorEvent>()

    single<EventsListener<ErrorEvent>>(errorDomainModuleName) {
        handler
    }

    single<EventsDispatcher<ErrorEvent>>(errorDomainModuleName) {
        handler
    }
}
