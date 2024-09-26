package com.che.architecture.features.payments.mvi

import com.che.architecture.domain.model.Price
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal data class PaymentsState(
    val prices: PersistentList<Price> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)
