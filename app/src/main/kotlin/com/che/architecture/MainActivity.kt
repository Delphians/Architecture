package com.che.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.chart.di.ChartNavigationModule
import com.che.architecture.features.homepage.di.HomepageNavigationModule
import com.che.architecture.features.payments.di.PaymentsNavigationModule
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import com.che.architecture.features.shared.di.AppModule
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.navigation.TabNavigation
import com.che.architecture.ui.compose.bottom.BottomNavigationBar
import com.che.architecture.ui.compose.foundation.ArchitectureTheme
import com.che.architecture.ui.compose.tabs.BottomTab
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class MainActivity : ComponentActivity() {

    private lateinit var tabNavigation: TabNavigation

    private lateinit var navController: NavHostController

    private lateinit var appViewModel: MviViewModel<AppMviState, AppIntentions, AppUiEvent>

    private val navigationGraphs: Set<NavigationGraphBuilder> by lazy {
        setOf(
            HomepageNavigationModule.getHomepageNavigation(),
            PaymentsNavigationModule.getPaymentsNavigation(),
            ChartNavigationModule.getChartNavigation(),
        )
    }

    private val tabs = BottomTab.entries.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabNavigation = TabNavigation(navigationGraphs)

        appViewModel = AppModule.getAppViewModel().apply {
            start(lifecycleScope)
        }

        registerNavigationGraphBuilder()

        setContent {

            navController = rememberNavController()

            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute =
                navBackStackEntry.value?.destination?.route ?: tabNavigation.startDestination

            ArchitectureTheme {
                Scaffold(
                    bottomBar = { SetupBottomNavBar(currentRoute, tabs) }
                ) { innerPadding ->
                    tabNavigation.SetupTabNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }

        appViewModel.event.onEach {
            when (it) {
                is AppUiEvent.TabChanged -> {
                    navController.navigate(tabNavigation.bindWithRoute(it.activeTab)) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }.launchIn(lifecycleScope)
    }

    @Composable
    private fun SetupBottomNavBar(
        currentRoute: String,
        tabs: List<BottomTab>
    ) {
        BottomNavigationBar(
            currentTab = tabNavigation.routeBindWithTab(currentRoute),
            tabs = tabs
        ) {
            appViewModel.dispatchIntention(
                AppIntentions.TabChangedIntention(it)
            )
        }
    }

    private fun registerNavigationGraphBuilder() {
        navigationGraphs.forEach(lifecycle::addObserver)
    }

    override fun onDestroy() {
        navigationGraphs.forEach(lifecycle::removeObserver)
        super.onDestroy()
    }
}
