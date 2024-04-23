package com.che.architecture.features.payments.navigation

import com.che.architecture.features.shared.navigation.Graph

internal sealed interface PaymentsGraph : Graph {
    data object PaymentsScreen : PaymentsGraph {
        override val route: String = "payments"
    }
}
