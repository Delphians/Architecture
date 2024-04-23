package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<MviState : Any, Intention : Any, Event : Any> {

    val event: SharedFlow<Event>

    val state: StateFlow<MviState>

    fun start(scope: CoroutineScope)

    fun stop()

    fun dispatchIntention(intention: Intention)
}
