package com.che.architecture.data.remote

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed interface Result<out V, out E> {
    operator fun component1(): V?
    operator fun component2(): E?
}

class Success<out V>(val value: V) : Result<V, Nothing> {

    override fun component1(): V? = value

    override fun component2(): Nothing? = null

    override fun hashCode(): Int = value.hashCode()
    override fun toString(): String = "Success($value)"
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

class Failure<out E>(val error: E) : Result<Nothing, E> {

    override fun component1(): Nothing? = null

    override fun component2(): E? = error

    override fun hashCode(): Int = error.hashCode()
    override fun toString(): String = "Failure($error)"
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <V, E, U> Result<V, E>.mapBoth(success: (V) -> U, failure: (E) -> U): U {
    contract {
        callsInPlace(success, InvocationKind.AT_MOST_ONCE)
        callsInPlace(failure, InvocationKind.AT_MOST_ONCE)
    }

    return when (this) {
        is Success -> success(value)
        is Failure -> failure(error)
    }
}
