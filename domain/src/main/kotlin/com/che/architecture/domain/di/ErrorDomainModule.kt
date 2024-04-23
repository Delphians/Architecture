package com.che.architecture.domain.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent
import dagger.Binds
import dagger.Module

@Module
abstract class ErrorDomainModule {

    @Binds
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<ErrorEvent>
    ): EventsListener<ErrorEvent>

    @Binds
    internal abstract fun bindsUIEventDispatcher(
        handler: DefaultEventsHandler<ErrorEvent>
    ): EventsDispatcher<ErrorEvent>
}
