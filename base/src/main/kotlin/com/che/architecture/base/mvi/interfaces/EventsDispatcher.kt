package com.che.architecture.base.mvi.interfaces

interface EventsDispatcher<Event : Any> {
    fun dispatch(event: Event): Boolean
}
