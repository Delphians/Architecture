package com.che.architecture.features.chart.mvi

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList

@Immutable
internal sealed interface ChartIntention {
    data class InitialIntention(
        val points: PersistentList<Double>
    ) : ChartIntention
}
