package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow

interface IntentionProcessor<State : Any, Intention : Any> {

    fun process(intentions: Flow<Intention>): Flow<MviResult<State>> = emptyFlow()

    fun process(intentions: Flow<Intention>, state: StateFlow<State>): Flow<MviResult<State>> =
        emptyFlow()
}
