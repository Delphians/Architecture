package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.base.mvi.interfaces.StateStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultStateStore<State : Any>(
    private val initialState: State
) : StateStore<State> {

    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    override suspend fun process(mviResult: MviResult<State>) {
        _state.emit(mviResult.reduce(state.value))
    }

    override fun isInitialState(): Boolean = initialState == state.value
}
