package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.features.payments.mvi.EmptyResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class EmptyIntentionProcessor @Inject constructor() :
    IntentionProcessor<PaymentsState, PaymentsIntention> {

    override fun process(intentions: Flow<PaymentsIntention>): Flow<MviResult<PaymentsState>> =
        intentions.filterIsInstance<PaymentsIntention.EmptyIntention>()
            .map {
                EmptyResults
            }
}
