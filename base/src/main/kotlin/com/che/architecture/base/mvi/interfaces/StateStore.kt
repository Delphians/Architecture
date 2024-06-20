package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.flow.StateFlow

interface StateStore<State : Any> {
    val state: StateFlow<State>
    suspend fun process(mviResult: MviResult<State>)
    fun isInitialState(): Boolean
}
