package com.che.architecture.features.payments.di

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import com.che.architecture.features.payments.mvi.processor.EmptyIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.FailureIntentionProcessor
import com.che.architecture.features.payments.mvi.processor.GetTickerPriceIntentionProcessor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
internal abstract class PaymentsProcessorsModule {

    @IntoSet
    @Binds
    internal abstract fun bindsInitialIntentionProcessor(
        it: GetTickerPriceIntentionProcessor
    ): IntentionProcessor<PaymentsState, PaymentsIntention>

    @IntoSet
    @Binds
    internal abstract fun bindsFailureIntentionProcessor(
        it: FailureIntentionProcessor
    ): IntentionProcessor<PaymentsState, PaymentsIntention>

    @IntoSet
    @Binds
    internal abstract fun bindsEmptyIntentionProcessor(
        it: EmptyIntentionProcessor
    ): IntentionProcessor<PaymentsState, PaymentsIntention>
}
