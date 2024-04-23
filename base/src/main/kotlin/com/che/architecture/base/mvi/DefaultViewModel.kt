package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.base.mvi.interfaces.StateStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn

class DefaultViewModel<MviState : Any, Intention : Any, Event : Any>(
    private val stateStore: StateStore<MviState>,
    private val eventsListener: EventsListener<Event>,
    private val intentionProcessors: Set<@JvmSuppressWildcards IntentionProcessor<MviState, Intention>>,
    private val intentionDispatcher: IntentionDispatcher<Intention>,
    private val initialIntention: Intention? = null
) : MviViewModel<MviState, Intention, Event> {

    private var viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)

    init {
        startProcessors()
    }

    override val event: SharedFlow<Event> by lazy {
        eventsListener.event.shareIn(
            scope = viewModelScope,
            SharingStarted.Lazily,
            0
        )
    }

    override val state: StateFlow<MviState> = stateStore.state

    override fun start(scope: CoroutineScope) {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun dispatchIntention(intention: Intention) {
        intentionDispatcher.dispatchIntention(viewModelScope, intention)
    }

    private fun startProcessors() {
        val intentionsFlow =
            intentionDispatcher.intentions.onStart { initialIntention?.let { event -> emit(event) } }

        intentionProcessors.flatMap { processor ->
            listOf(
                processor.process(intentionsFlow),
                processor.process(intentionsFlow, stateStore.state)
            )
        }.merge()
            .onEach { result ->
                stateStore.process(result)
            }.launchIn(viewModelScope)
    }
}
