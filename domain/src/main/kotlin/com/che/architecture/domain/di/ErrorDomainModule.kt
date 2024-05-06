package com.che.architecture.domain.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.domain.model.ErrorEvent
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ErrorDomainModule {

    @Binds
    @Singleton
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<ErrorEvent>
    ): EventsListener<ErrorEvent>

    @Binds
    @Singleton
    internal abstract fun bindsUIEventDispatcher(
        handler: DefaultEventsHandler<ErrorEvent>
    ): EventsDispatcher<ErrorEvent>
}
