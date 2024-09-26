package com.che.architecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.che.architecture.atomic.design.tabs.BottomTab
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

internal class TabNavigation(
    private val navigationGraphBuilders: Set<NavigationGraphBuilder>
) {

    val startDestination = bindWithRoute(BottomTab.HOME)

    @Composable
    internal fun SetupTabNavigation(
        modifier: Modifier = Modifier,
        navController: NavHostController
    ) {
        NavHost(
            navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            navigationGraphBuilders.forEach {
                it.setupGraph(
                    navGraphBuilder = this,
                    modifier = modifier
                )
            }
        }
    }

    internal fun bindWithRoute(tab: BottomTab): String {
        var destination: String? = null
        navigationGraphBuilders.forEach {
            if (tab.name.equals(it.route, ignoreCase = true)) {
                destination = it.route
            }
        }
        return destination ?: bindWithRoute(BottomTab.HOME)
    }

    internal fun routeBindWithTab(route: String): BottomTab = when {
        route.equals(BottomTab.CHART.name, true) -> BottomTab.CHART
        route.equals(BottomTab.HOME.name, true) -> BottomTab.HOME
        route.equals(BottomTab.PAYMENTS.name, ignoreCase = true) -> BottomTab.PAYMENTS
        else -> throw IllegalArgumentException(" Something went wrong")
    }
}
