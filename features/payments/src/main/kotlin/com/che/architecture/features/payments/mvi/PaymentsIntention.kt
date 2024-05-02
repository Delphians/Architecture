package com.che.architecture.features.payments.mvi

import com.che.architecture.domain.model.Ticker
import java.time.LocalDate

internal sealed interface PaymentsIntention {
    data class GetTickerPriceIntention(
        val ticker: Ticker,
        val dateRange: ClosedRange<LocalDate>
    ) : PaymentsIntention

    data class FailureIntention(
        val failure: Throwable
    ) : PaymentsIntention

    data object EmptyIntention : PaymentsIntention
}
