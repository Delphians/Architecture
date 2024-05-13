package com.che.architecture.features.homepage.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.homepage.di.HomepageModule.getViewModel
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class HomepageNavigationImpl : NavigationGraphBuilder {

    override val startDestination: String = HomepageGraph.HomeScreen.route

    private lateinit var viewModel: MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>

    private lateinit var coroutineScope: CoroutineScope

    override fun onStart(owner: LifecycleOwner) {
        coroutineScope = CoroutineScope(Dispatchers.Default)
        viewModel = getViewModel().also {
            it.start(coroutineScope)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        coroutineScope.cancel()
    }

    override fun setupGraph(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.run {
            handleScreenDetails()
            handleHomeScreenDetails()
            handleUiEvent(coroutineScope, navController)
        }
    }

    private fun NavGraphBuilder.handleScreenDetails() {
        composable(startDestination) {
            HomeScreen {
                viewModel.dispatchIntention(
                    HomepageIntention.OpenScreenDetails(it)
                )
            }
        }
    }

    private fun NavGraphBuilder.handleHomeScreenDetails() {
        composable(
            route = HomeScreenDetails.screenRouteWithArgs,
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

    private fun handleUiEvent(coroutineScope: CoroutineScope, controller: NavController) {
        viewModel.event.onEach {
            when (it) {
                is HomepageUiEvent.NavigateToDetails -> controller.navigate(
                    "${HomeScreenDetails.route}/${it.ticker.value}"
                )
            }
        }.launchIn(coroutineScope)
    }
}
