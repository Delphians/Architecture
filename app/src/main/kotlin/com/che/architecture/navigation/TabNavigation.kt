package com.che.architecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.che.architecture.features.chart.navigation.ChartNavigation
import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.ui.compose.tabs.BottomTab
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TabNavigation @Inject constructor(
    private val homepageNavigation: HomepageNavigation,
    private val paymentsNavigation: PaymentsNavigation,
    private val chartNavigation: ChartNavigation
) {

    val startDestination = homepageNavigation.startDestination

    @Composable
    internal fun SetupTabNavigation(
        navController: NavHostController,
        modifier: Modifier = Modifier
    ) {
        NavHost(
            navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            homepageNavigation.setupGraph(
                navGraphBuilder = this,
                modifier = modifier
            )
            paymentsNavigation.setupGraph(
                navGraphBuilder = this,
                modifier = modifier
            )
            chartNavigation.setupGraph(
                navGraphBuilder = this,
                modifier = modifier
            )
        }
    }

    internal fun bindWithRoute(tab: BottomTab) = when {
        tab.name.equals(
            homepageNavigation.startDestination,
            ignoreCase = true
        ) -> homepageNavigation.startDestination

        tab.name.equals(
            paymentsNavigation.startDestination,
            ignoreCase = true
        ) -> paymentsNavigation.startDestination

        tab.name.equals(
            chartNavigation.startDestination,
            ignoreCase = true
        ) -> chartNavigation.startDestination

        else -> throw IllegalArgumentException(" Something went wrong")
    }

    internal fun routeBindWithTab(route: String): BottomTab = when {
        route.equals(BottomTab.CHART.name, true) -> BottomTab.CHART
        route.equals(BottomTab.HOME.name, true) -> BottomTab.HOME
        route.equals(BottomTab.PAYMENTS.name, ignoreCase = true) -> BottomTab.PAYMENTS
        else -> throw IllegalArgumentException(" Something went wrong")
    }
}
