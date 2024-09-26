package com.che.architecture.features.payments.mvi

import androidx.compose.runtime.Immutable
import com.che.architecture.domain.model.Ticker
import kotlinx.datetime.LocalDate

internal sealed interface PaymentsIntention {
    @Immutable
    data class GetTickerPriceIntention(
        val ticker: Ticker,
        val dateRange: ClosedRange<LocalDate>
    ) : PaymentsIntention

    @Immutable
    data class FailureIntention(
        val failure: Throwable
    ) : PaymentsIntention

    data object EmptyIntention : PaymentsIntention
}
