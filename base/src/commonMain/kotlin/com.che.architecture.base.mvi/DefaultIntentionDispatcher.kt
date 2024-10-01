package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultIntentionDispatcher<Intention : Any> :
    IntentionDispatcher<Intention> {

    override val intentions: MutableSharedFlow<Intention> = MutableSharedFlow(
        replay = MAX_CACHE_SIZE,
        extraBufferCapacity = MAX_BUFFER_SIZE
    )

    override fun dispatchIntention(intention: Intention) {
        intentions.tryEmit(intention)
    }

    companion object {
        private const val MAX_BUFFER_SIZE = 1000
        private const val MAX_CACHE_SIZE = 1
    }
}
