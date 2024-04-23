package com.che.architecture.features.payments.mvi

import com.che.architecture.domain.model.Price

internal data class PaymentsState(
    val prices: List<Price> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
