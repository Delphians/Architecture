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
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageIntention
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageState
import com.che.architecture.features.homepage.mvi.homeScreen.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Reusable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@Reusable
internal class HomepageNavigation @Inject constructor(
    private val viewModel: MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>
) : NavigationGraphBuilder {

    override val route: String = HOMEPAGE_GRAPH_ROUTE

    private var navHostController: NavHostController? = null

    override fun onCreate(owner: LifecycleOwner) {
        viewModel.start(owner.lifecycleScope)
        handleUiEvent()
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
