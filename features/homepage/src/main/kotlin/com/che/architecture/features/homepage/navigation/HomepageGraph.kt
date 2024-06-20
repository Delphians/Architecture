package com.che.architecture.features.homepage.navigation

import androidx.navigation.navDeepLink
import com.che.architecture.features.shared.navigation.Graph

internal const val HOMEPAGE_GRAPH_ROUTE = "home"
internal sealed interface HomepageGraph : Graph {
    data object HomeScreen : HomepageGraph {
        override val destination: String = "home_screen"
    }

    data object HomeScreenDetails : HomepageGraph {
        override val destination: String = "home_screen_details"
        val homeScreenDetailsArgs = HomeScreenDetailsArgs()
        val destinationWithArgs: String =
            "$destination/{${homeScreenDetailsArgs.ticker}}"
        val deepLinks = listOf(
            navDeepLink { uriPattern = "architecture://$destinationWithArgs" }
        )
    }
}
