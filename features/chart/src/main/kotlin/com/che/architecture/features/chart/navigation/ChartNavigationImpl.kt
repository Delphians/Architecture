package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.chart.di.ChartModule.getViewModel
import com.che.architecture.features.chart.mvi.ChartIntention
import com.che.architecture.features.chart.mvi.ChartState
import com.che.architecture.features.chart.mvi.ChartUiEvent
import com.che.architecture.features.chart.screens.ChartScreen
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.ui.compose.molecules.WarningScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

internal class ChartNavigationImpl : NavigationGraphBuilder {

    override val startDestination: String = ChartGraph.ChartRouteScreen.route

    private lateinit var viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>

    private lateinit var coroutineScope: CoroutineScope

    override fun onStart(owner: LifecycleOwner) {
        coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        viewModel = getViewModel().also {
            it.start(coroutineScope)
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        viewModel.dispatchIntention(
            ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints)
        )
    }

    override fun onStop(owner: LifecycleOwner) {
        coroutineScope.cancel()
    }

    override fun setupGraph(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable(startDestination) {
            viewModel.state.collectAsState().value.let { state ->
                if (state.points.isEmpty()) {
                    WarningScreen()
                } else {
                    ChartScreen(chartState = state)
                }
            }
        }
    }
}
