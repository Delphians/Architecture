package com.che.architecture.features.chart.di

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.processors.InitialIntentionProcessor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
internal abstract class ChartProcessorsModule {

    @IntoSet
    @Binds
    internal abstract fun bindsInitialIntentionProcessor(
        it: InitialIntentionProcessor
    ): IntentionProcessor<ChartState, ChartIntention>
}
