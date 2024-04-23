package com.che.architecture.features.homepage.navigation

import androidx.navigation.navDeepLink
import com.che.architecture.features.shared.navigation.Graph

internal sealed interface HomepageGraph : Graph {
    data object HomeScreen : HomepageGraph {
        override val route: String = "home"
    }

    data object HomeScreenDetails : HomepageGraph {
        override val route: String = "home_screen_details"
        val homeScreenDetailsArgs = HomeScreenDetailsArgs()
        val screenRouteWithArgs: String =
            "$route/{${homeScreenDetailsArgs.ticker}}"
        val deepLinks = listOf(
            navDeepLink { uriPattern = "architecture://$screenRouteWithArgs" }
        )
    }
}
