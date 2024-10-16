package com.che.architecture.features.chart.mvi.processors

import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.EmptyResults
import com.che.architecture.features.chart.mvi.SetPointsResults
import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform

internal class InitialIntentionProcessor :
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
