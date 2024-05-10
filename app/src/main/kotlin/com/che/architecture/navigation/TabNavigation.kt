package com.che.architecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.che.architecture.ui.compose.R
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.ui.compose.tabs.BottomTab

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
                    modifier = modifier,
                    navGraphBuilder = this,
                    navController = navController
                )
            }
        }
    }

    internal fun bindWithRoute(tab: BottomTab): String {
        var destination: String? = null
        navigationGraphBuilders.forEach {
            if (tab.name.equals(it.startDestination, ignoreCase = true)) {
                destination = it.startDestination
            }
        }
        return destination ?: bindWithRoute(BottomTab.HOME)
    }

    @Composable
    internal fun routeBindWithTab(route: String): BottomTab = when {
        route.equals(BottomTab.CHART.name, true) -> BottomTab.CHART
        route.equals(BottomTab.HOME.name, true) -> BottomTab.HOME
        route.equals(BottomTab.PAYMENTS.name, ignoreCase = true) -> BottomTab.PAYMENTS
        else -> throw IllegalArgumentException(stringResource(id = R.string.somethingWentWrong))
    }
}
