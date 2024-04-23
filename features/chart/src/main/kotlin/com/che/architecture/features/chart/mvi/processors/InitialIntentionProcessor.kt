package com.che.architecture.features.chart.mvi.processors

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.EmptyResults
import com.che.architecture.features.chart.mvi.SetPointsResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

internal class InitialIntentionProcessor @Inject constructor() :
    IntentionProcessor<ChartState, ChartIntention> {

    override fun process(intentions: Flow<ChartIntention>): Flow<MviResult<ChartState>> =
        intentions.filterIsInstance<ChartIntention.InitialIntention>()
            .transform {
                if (it.points.isEmpty()) {
                    emit(EmptyResults)
                } else {
                    emit(SetPointsResults(it.points))
                }
            }
}
