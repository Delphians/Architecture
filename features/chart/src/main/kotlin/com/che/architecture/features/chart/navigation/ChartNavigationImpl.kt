package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
import com.che.architecture.ui.compose.molecules.WarningScreen
import javax.inject.Inject
import javax.inject.Singleton

@SuppressWarnings("MagicNumber")
@Singleton
internal class ChartNavigationImpl @Inject constructor(
    private val viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>
) : ChartNavigation {

    override val startDestination: String = ChartGraph.ChartRouteScreen.route

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
                        ChartScreen(state)
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
