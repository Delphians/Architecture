package com.che.architecture.features.homepage.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.domain.model.Ticker
import com.che.architecture.features.homepage.mvi.HomepageIntention
import com.che.architecture.features.homepage.mvi.HomepageState
import com.che.architecture.features.homepage.mvi.HomepageUiEvent
import com.che.architecture.features.homepage.navigation.HomepageGraph.HomeScreenDetails
import com.che.architecture.features.homepage.screens.HomeScreen
import com.che.architecture.features.homepage.screens.HomeScreenDetails
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class HomepageNavigationImpl @Inject constructor(
    private val viewModel: MviViewModel<HomepageState, HomepageIntention, HomepageUiEvent>
) : HomepageNavigation {

    override val startDestination: String = HomepageGraph.HomeScreen.route
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
                composable(startDestination) {
                    HomeScreen {
                        viewModel.dispatchIntention(
                            HomepageIntention.OpenScreenDetails(it)
                        )
                    }
                }
                composable(
                    route = HomeScreenDetails.screenRouteWithArgs,
                    arguments = HomeScreenDetails.homeScreenDetailsArgs.args,
                    deepLinks = HomeScreenDetails.deepLinks
                ) {
                    it.arguments?.getString(HomeScreenDetails.homeScreenDetailsArgs.ticker)?.let {
                        HomeScreenDetails(Ticker(it))
                    }
                }
            }

            LaunchedEffect(key1 = Unit) {
                viewModel.event.onEach {
                    handleUiEvent(it, homeNavController)
                }.launchIn(this)
            }
        }
    }

    private fun handleUiEvent(event: HomepageUiEvent, controller: NavHostController) {
        when (event) {
            is HomepageUiEvent.NavigateToDetails -> controller.navigate(
                "${HomeScreenDetails.route}/${event.ticker.value}"
            )
        }
    }
}
