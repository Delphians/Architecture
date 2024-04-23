package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.base.mvi.interfaces.StateStore
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@AssistedFactory
interface DefaultStateStoreFactory<State : Any> {
    fun create(
        initialState: State
    ): DefaultStateStore<State>
}

class DefaultStateStore<State : Any> @AssistedInject constructor(
    @Assisted initialState: State
) : StateStore<State> {

    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    override suspend fun process(mviResult: MviResult<State>) {
        _state.emit(mviResult.reduce(state.value))
    }
}
