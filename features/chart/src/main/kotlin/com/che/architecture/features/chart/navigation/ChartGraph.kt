package com.che.architecture.features.chart.navigation

import com.che.architecture.features.shared.navigation.Graph

internal const val CHART_GRAPH_ROUTE = "chart"
internal sealed interface ChartGraph : Graph {
    data object ChartRouteScreen : ChartGraph {
        override val destination: String = "chart_screen"
    }
}
