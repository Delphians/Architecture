package com.che.architecture.features.chart.mvi

import com.che.architecture.base.mvi.interfaces.MviResult
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal data class SetPointsResults(
    private val points: PersistentList<Double>
) : MviResult<ChartState> {
    override fun reduce(state: ChartState): ChartState =
        state.copy(
            points = points,
            isLoading = false
        )
}

internal data object EmptyResults : MviResult<ChartState> {
    override fun reduce(state: ChartState): ChartState =
        state.copy(
            points = persistentListOf(),
            isLoading = false
        )
}

internal data object LoadingResults : MviResult<ChartState> {
    override fun reduce(state: ChartState): ChartState =
        state.copy(
            isLoading = true
        )
}
