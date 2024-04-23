package com.che.architecture.features.chart.mvi

internal data class ChartState(
    val points: List<Double> = emptyList(),
    val isLoading: Boolean = false
)
