package com.che.architecture.features.chart.mvi

import com.che.architecture.base.mvi.interfaces.MviResult

internal data class SetPointsResults(
    private val points: List<Double>
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
            points = emptyList(),
            isLoading = false
        )
}

internal data object LoadingResults : MviResult<ChartState> {
    override fun reduce(state: ChartState): ChartState =
        state.copy(
            isLoading = true
        )
}
