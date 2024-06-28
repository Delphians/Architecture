package com.che.architecture.features.chart.mvi

import kotlinx.collections.immutable.PersistentList

internal sealed interface ChartIntention {
    data class InitialIntention(
        val points: PersistentList<Double>
    ) : ChartIntention
}
