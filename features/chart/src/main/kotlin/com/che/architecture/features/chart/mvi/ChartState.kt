package com.che.architecture.features.chart.mvi

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

internal data class ChartState(
    val points: PersistentList<Double> = persistentListOf(),
    val isLoading: Boolean = false
)
