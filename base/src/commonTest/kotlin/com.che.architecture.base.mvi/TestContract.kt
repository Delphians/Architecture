package com.che.architecture.base.mvi

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import kotlinx.coroutines.flow.Flow

internal sealed class TestIntention {
    data class PlusIntention(val value: Int) : TestIntention()

    data class MinusIntention(val value: Int) : TestIntention()
}

internal data class TestState(val finalCount: Int)

internal sealed class TestEvent {
    data class Plus(
        val value: Int
    ) : TestEvent()

    data class Minus(
        val value: Int
    ) : TestEvent()
}

internal inline fun <StateT : Any, IntentionT : Any> createProcessor(
    crossinline block: (Flow<IntentionT>) -> Flow<MviResult<StateT>>
): IntentionProcessor<StateT, IntentionT> =
    object : IntentionProcessor<StateT, IntentionT> {
        override fun process(intentions: Flow<IntentionT>): Flow<MviResult<StateT>> =
            block(intentions)
    }

internal class PlusTestMviResult(val value: Int) : MviResult<TestState> {
    override fun reduce(state: TestState): TestState = state.copy(
        state.finalCount + value
    )
}

internal class MinusTestMviResult(val value: Int) : MviResult<TestState> {
    override fun reduce(state: TestState): TestState = state.copy(
        state.finalCount - value
    )
}
