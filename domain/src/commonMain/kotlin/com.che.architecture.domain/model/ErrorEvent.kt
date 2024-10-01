package com.che.architecture.domain.model

import kotlinx.datetime.LocalDate

sealed interface ErrorEvent {
    val cause: Throwable?

    data class TiingoPriceError(
        val ticker: Ticker,
        val dateRange: ClosedRange<LocalDate>,
        override val cause: Throwable? = null,
    ) : ErrorEvent
}
