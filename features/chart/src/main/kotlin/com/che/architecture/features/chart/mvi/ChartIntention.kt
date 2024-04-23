package com.che.architecture.features.chart.mvi

internal sealed interface ChartIntention {
    data class InitialIntention(
        val points: List<Double>
    ) : ChartIntention
}
