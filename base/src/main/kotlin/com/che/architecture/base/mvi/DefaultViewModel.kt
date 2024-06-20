package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.base.mvi.interfaces.StateStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class DefaultViewModel<MviState : Any, Intention : Any, Event : Any>(
    private val stateStore: StateStore<MviState>,
    private val eventsListener: EventsListener<Event>,
    private val intentionProcessors: Set<@JvmSuppressWildcards IntentionProcessor<MviState, Intention>>,
    private val intentionDispatcher: IntentionDispatcher<Intention>,
    private val initialIntention: Intention? = null
) : MviViewModel<MviState, Intention, Event> {

    private lateinit var _viewModelScope: CoroutineScope

    override val event: Flow<Event> = eventsListener.event

    override val state: StateFlow<MviState> = stateStore.state

    override fun getScope(): CoroutineScope = _viewModelScope

    override fun start(scope: CoroutineScope) {
        intentionDispatcher.intentions.resetReplayCache()
        eventsListener.resetCache()
        _viewModelScope = scope
        startProcessors()
    }

    override fun stop() {
        _viewModelScope.cancel()
    }

    override fun dispatchIntention(intention: Intention) {
        intentionDispatcher.dispatchIntention(intention)
    }

    private fun startProcessors() {
        val intentionsFlow =
            intentionDispatcher.intentions
                .onStart {
                    if (stateStore.isInitialState()) {
                        initialIntention?.let { event -> emit(event) }
                    }
                }

        intentionProcessors.flatMap { processor ->
            listOf(
                processor.process(intentionsFlow),
                processor.process(intentionsFlow, stateStore.state)
            )
        }.merge()
            .onEach { result ->
                stateStore.process(result)
            }.launchIn(_viewModelScope)
    }
}
