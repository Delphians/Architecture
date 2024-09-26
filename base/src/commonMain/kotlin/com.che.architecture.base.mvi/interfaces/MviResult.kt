package com.che.architecture.base.mvi.interfaces

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface MviResult<State : Any> {
    fun reduce(state: State): State
}

fun <StateT : Any> emptyResult(): MviResult<StateT> =
    object : MviResult<StateT> {
        override fun reduce(state: StateT): StateT = state
        override fun equals(other: Any?): Boolean = true
        override fun hashCode(): Int = 1
    }

fun <StateT : Any> Flow<*>.ignoreResult(): Flow<MviResult<StateT>> =
    map { emptyResult() }
