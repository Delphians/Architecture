package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.flow.Flow

interface EventsListener<Event> {
    val event: Flow<Event>
}
