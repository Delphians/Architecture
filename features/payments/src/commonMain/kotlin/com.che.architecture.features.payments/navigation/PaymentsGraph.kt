package com.che.architecture.features.payments.navigation

import com.che.architecture.features.shared.navigation.Graph

internal const val PAYMENTS_GRAPH_ROUTE = "payments"

internal sealed interface PaymentsGraph : Graph {
    data object PaymentsScreen : PaymentsGraph {
        override val destination: String = "payments_screen"
    }
}
