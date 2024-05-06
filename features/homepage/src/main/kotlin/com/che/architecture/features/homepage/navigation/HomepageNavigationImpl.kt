package com.che.architecture.features.homepage.navigation

import androidx.compose.runtime.LaunchedEffect
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
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@Reusable
internal class HomepageNavigationImpl @Inject constructor(
    private val viewModel: MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>
) : NavigationGraphBuilder {

    override val startDestination: String = HomepageGraph.HomeScreen.route

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
            val homeNavController = rememberNavController()

            NavHost(
                navController = homeNavController,
                startDestination = startDestination
            ) {
                handleScreenDetails()
                handleHomeScreenDetails()
            }

            LaunchedEffect(key1 = Unit) {
                handleUiEvent(homeNavController)
            }
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

    private fun CoroutineScope.handleUiEvent(controller: NavHostController) {
        viewModel.event.onEach {
            when (it) {
                is HomepageUiEvent.NavigateToDetails -> controller.navigate(
                    "${HomeScreenDetails.route}/${it.ticker.value}"
                )
            }
        }.launchIn(this)
    }
}
