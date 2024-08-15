package com.che.architecture.features.homepage.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.homepage.di.HomepageModule.getViewModel
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreen
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class HomepageNavigation : NavigationGraphBuilder {

    override val route: String = HOMEPAGE_GRAPH_ROUTE

    private var navHostController: NavHostController? = null

    private lateinit var viewModel: MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>

    override fun onStart(owner: LifecycleOwner) {
        viewModel.start(owner.lifecycleScope)
        viewModel = getViewModel().also {
            it.start(viewModel.getScope())
        }
        handleUiEvent()
    }

    override fun onStop(owner: LifecycleOwner) {
        viewModel.stop()
    }

    override fun setupGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            navHostController = rememberNavController().apply {
                NavHost(
                    navController = this,
                    startDestination = HomeScreen.destination
                ) {
                    handleHomeScreen()
                    handleHomeScreenDetails()
                }
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
            arguments = HomeScreenDetails.homeScreenDetailsArgs.args,
            deepLinks = HomeScreenDetails.deepLinks
        ) {
            it.arguments?.getString(HomeScreenDetails.homeScreenDetailsArgs.ticker)?.let {
                HomeScreenDetails(
                    ticker = Ticker(it)
                )
            }
        }
    }

    private fun handleUiEvent() {
        viewModel.event.onEach {
            when (it) {
                is HomepageUiEvent.NavigateToDetails -> navHostController?.navigate(
                    "${HomeScreenDetails.destination}/${it.ticker.value}"
                )
            }
        }.launchIn(viewModel.getScope())
    }
}
