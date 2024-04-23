package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultIntentionDispatcher<Intention : Any> @Inject constructor() :
    IntentionDispatcher<Intention> {

    override val intentions: MutableSharedFlow<Intention> = MutableSharedFlow(
        replay = MAX_CACHE_SIZE,
        extraBufferCapacity = MAX_BUFFER_SIZE
    )

    override fun dispatchIntention(scope: CoroutineScope, intention: Intention) {
        scope.launch {
            intentions.emit(intention)
        }
    }

    companion object {
        private const val MAX_BUFFER_SIZE = 1000
        private const val MAX_CACHE_SIZE = 1
    }
}
