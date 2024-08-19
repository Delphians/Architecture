package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.chart.di.ChartModule.getViewModel
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.navigation.ChartGraph.ChartRouteScreen.destination
import com.che.architecture.features.chart.screens.ChartScreen
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.ui.compose.molecules.WarningScreen

internal class ChartNavigation : NavigationGraphBuilder {

    override val route: String = CHART_GRAPH_ROUTE

    private lateinit var viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>

    override fun onStart(owner: LifecycleOwner) {
        viewModel = getViewModel().apply {
            start(owner.lifecycleScope)
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        viewModel.dispatchIntention(
            ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints)
        )
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel.stop()
    }

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            val navHostController = rememberNavController()
            val state = viewModel.state.collectAsState().value

            NavHost(
                navController = navHostController,
                startDestination = destination
            ) {
                composable(destination) {
                    if (state.points.isNotEmpty()) {
                        ChartScreen(chartState = state)
                    } else {
                        WarningScreen()
                    }
                }
            }

            viewModel.dispatchIntention(
                ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints)
            )
        }
    }
}
