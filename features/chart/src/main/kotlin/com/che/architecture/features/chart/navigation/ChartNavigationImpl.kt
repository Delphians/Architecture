package com.che.architecture.features.chart.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.DefaultViewModel
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.features.chart.di.ChartModule.getChartProcessors
import com.che.architecture.features.chart.di.ChartModule.getEventListener
import com.che.architecture.features.chart.di.ChartModule.getIntentionDispatcher
import com.che.architecture.features.chart.di.ChartModule.getStateStore
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

    private var viewModel: MviViewModel<ChartState, ChartIntention, ChartUiEvent>? = null

    override fun onStart(owner: LifecycleOwner) {
        viewModel = DefaultViewModel(
            stateStore = getStateStore(),
            eventsListener = getEventListener(),
            intentionProcessors = getChartProcessors(),
            intentionDispatcher = getIntentionDispatcher()
        ).also {
            it.start(CoroutineScope(Dispatchers.Unconfined))
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel?.stop()
        viewModel = null
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
                    viewModel?.state?.collectAsState()?.value?.let { state ->
                        ChartScreen(chartState = state)
                    } ?: WarningScreen()
                }
            }

            LaunchedEffect(key1 = Unit) {
                viewModel?.dispatchIntention(
                    ChartIntention.InitialIntention(FakeStockData.fakeClosePricePoints)
                )
            }
        }
    }
}
