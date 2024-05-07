package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
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
import com.che.architecture.features.chart.screens.ChartScreen
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.ui.compose.molecules.WarningScreen
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@Reusable
internal class ChartNavigationImpl @Inject constructor() : NavigationGraphBuilder {

    override val startDestination: String = ChartGraph.ChartRouteScreen.route

    @Stable
    private lateinit var viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>

    override fun onStart(owner: LifecycleOwner) {
        viewModel = getViewModel().also {
            it.start(CoroutineScope(Dispatchers.Unconfined))
        }
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

            NavHost(
                navController = chartNavController,
                startDestination = startDestination
            ) {
                composable(startDestination) {
                    viewModel.state.collectAsState().value.let { state ->
                        if (state.points.isEmpty()) {
                            WarningScreen()
                        } else {
                            ChartScreen(chartState = state)
                        }
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
