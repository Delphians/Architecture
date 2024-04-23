package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow

interface IntentionDispatcher<Intention : Any> {

    val intentions: MutableSharedFlow<Intention>

    fun dispatchIntention(scope: CoroutineScope, intention: Intention)
}
