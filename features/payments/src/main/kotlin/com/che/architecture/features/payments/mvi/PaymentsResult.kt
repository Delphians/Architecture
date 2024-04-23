package com.che.architecture.features.payments.mvi

import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.domain.model.Price

internal data class DailyPriceResults(
    val prices: List<Price>
) : MviResult<PaymentsState> {
    override fun reduce(state: PaymentsState): PaymentsState =
        state.copy(
            prices = prices,
            isLoading = false,
            error = null
        )
}

internal data object EmptyResults : MviResult<PaymentsState> {
    override fun reduce(state: PaymentsState): PaymentsState =
        state.copy(
            prices = emptyList(),
            isLoading = false,
            error = null
        )
}

internal data object LoadingResults : MviResult<PaymentsState> {
    override fun reduce(state: PaymentsState): PaymentsState =
        state.copy(
            prices = emptyList(),
            isLoading = true,
            error = null
        )
}

internal data class FailureResults(val throwable: Throwable) : MviResult<PaymentsState> {
    override fun reduce(state: PaymentsState): PaymentsState =
        state.copy(
            prices = emptyList(),
            isLoading = false,
            error = throwable
        )
}
