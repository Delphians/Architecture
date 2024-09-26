package com.che.architecture.domain.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent

object ErrorDomainModule {

    private val eventsHandler = DefaultEventsHandler<ErrorEvent>()

    fun provideEventListener(): EventsListener<ErrorEvent> = eventsHandler

    fun provideEventDispatcher(): EventsDispatcher<ErrorEvent> = eventsHandler
}
