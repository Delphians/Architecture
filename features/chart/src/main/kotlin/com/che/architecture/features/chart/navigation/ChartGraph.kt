package com.che.architecture.features.chart.navigation

import com.che.architecture.features.shared.navigation.Graph

internal sealed interface ChartGraph : Graph {
    data object ChartRouteScreen : ChartGraph {
        override val route: String = "chart"
    }
}
