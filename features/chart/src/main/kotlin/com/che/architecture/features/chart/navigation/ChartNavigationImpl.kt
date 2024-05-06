package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.LaunchedEffect
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
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.screens.ChartScreen
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.ui.compose.molecules.WarningScreen
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class ChartNavigationImpl @Inject constructor(
    private val viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>
) : NavigationGraphBuilder {

    override val startDestination: String = ChartGraph.ChartRouteScreen.route

    override fun onStart(owner: LifecycleOwner) {
        viewModel.start(owner.lifecycleScope)
    }
    override fun onStop(owner: LifecycleOwner) {
        viewModel.stop()
    }

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(startDestination) {
            val chartNavController = rememberNavController()
            val state = viewModel.state.collectAsState().value

            NavHost(
                navController = chartNavController,
                startDestination = startDestination
            ) {
                composable(startDestination) {
                    if (state.points.isNotEmpty()) {
                        ChartScreen(chartState = state)
                    } else {
                        WarningScreen()
                    }
                }
            }

            LaunchedEffect(key1 = Unit) {
                viewModel.dispatchIntention(
                    ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints)
                )
            }
        }
    }
}
