package com.che.architecture.features.homepage.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.homepage.di.loadModules
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreen
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class HomepageNavigation : NavigationGraphBuilder, KoinComponent {

    override val route: String = HOMEPAGE_GRAPH_ROUTE

    private val viewModel:
            MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent> by inject()

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            val navHostController = rememberNavController()

            NavHost(
                navController = navHostController,
                startDestination = HomeScreen.destination
            ) {
                handleHomeScreen()
                handleHomeScreenDetails()
            }

            val scope = LocalLifecycleOwner.current.lifecycleScope

            LifecycleEventEffect(Lifecycle.Event.ON_START) {
                loadModules()
                viewModel.start(scope)
                viewModel.event.onEach {
                    when (it) {
                        is HomepageUiEvent.NavigateToDetails -> navHostController.navigate(
                            "${HomeScreenDetails.destination}/${it.ticker.value}"
                        )
                    }
                }.launchIn(viewModel.getScope())
            }

            LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
                viewModel.stop()
            }
        }
    }

    private fun NavGraphBuilder.handleHomeScreen() {
        composable(HomeScreen.destination) {
            HomeScreen {
                viewModel.dispatchIntention(
                    HomepageIntention.OpenScreenDetails(it)
                )
            }
        }
    }

    private fun NavGraphBuilder.handleHomeScreenDetails() {
        composable(
            route = HomeScreenDetails.destinationWithArgs,
            arguments = HomeScreenDetails.homeScreenDetailsArgs.args
        ) {
            it.arguments?.getString(HomeScreenDetails.homeScreenDetailsArgs.ticker)?.let {
                HomeScreenDetails(
                    ticker = Ticker(it)
                )
            }
        }
    }
}
