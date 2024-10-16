package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultEventsHandler<Event : Any> :
    EventsListener<Event>,
    EventsDispatcher<Event> {

    private val _event = MutableSharedFlow<Event>(
        extraBufferCapacity = EXTRA_BUFFER_SIZE,
        replay = EXTRA_BUFFER_SIZE
    )

    override val event: Flow<Event> = _event

    override fun dispatch(event: Event): Boolean {
        return _event.tryEmit(event)
    }

    override fun resetCache() {
        _event.resetReplayCache()
    }

    private companion object {
        private const val EXTRA_BUFFER_SIZE = 15
    }
}
