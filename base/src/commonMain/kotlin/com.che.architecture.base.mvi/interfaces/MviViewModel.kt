package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<MviState : Any, Intention : Any, Event : Any> {

    val event: Flow<Event>

    val state: StateFlow<MviState>

    fun getScope(): CoroutineScope

    fun dispatchIntention(intention: Intention)

    fun start(scope: CoroutineScope)
}
