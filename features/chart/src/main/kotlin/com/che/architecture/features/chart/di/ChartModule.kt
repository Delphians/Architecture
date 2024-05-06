package com.che.architecture.features.chart.di

import com.che.architecture.base.mvi.DefaultEventsHandler
import com.che.architecture.base.mvi.DefaultIntentionDispatcher
import com.che.architecture.base.mvi.interfaces.EventsDispatcher
import com.che.architecture.base.mvi.interfaces.EventsListener
import com.che.architecture.base.mvi.interfaces.IntentionDispatcher
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.mvi.ChartViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(includes = [ChartProcessorsModule::class])
@InstallIn(ActivityComponent::class)
abstract class ChartModule {

    @Binds
    internal abstract fun bindsMviViewModel(
        it: ChartViewModel
    ): MviViewModel<ChartState, ChartIntention, ChartUiEvent>

    @Binds
    internal abstract fun bindsEventListener(
        handler: DefaultEventsHandler<ChartUiEvent>
    ): EventsListener<ChartUiEvent>

    @Binds
    internal abstract fun bindsEventDispatcher(
        handler: DefaultEventsHandler<ChartUiEvent>
    ): EventsDispatcher<ChartUiEvent>

    @Binds
    internal abstract fun bindsIntentionDispatcher(
        dispatcher: DefaultIntentionDispatcher<ChartIntention>
    ): IntentionDispatcher<ChartIntention>
}
