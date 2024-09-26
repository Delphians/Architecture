package com.che.architecture

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.che.architecture.atomic.design.bottom.BottomNavigationBar
import com.che.architecture.atomic.design.foundation.ArchitectureTheme
import com.che.architecture.atomic.design.tabs.BottomTab
import com.che.architecture.base.mvi.interfaces.MviViewModel
import com.che.architecture.features.homepage.di.HomepageNavigationModule
import com.che.architecture.features.shared.app.AppIntentions
import com.che.architecture.features.shared.app.AppMviState
import com.che.architecture.features.shared.app.AppUiEvent
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import com.che.architecture.navigation.TabNavigation
import com.che.architecture.features.chart.di.ChartNavigationModule
import com.che.architecture.features.payments.di.PaymentsNavigationModule
import com.che.architecture.features.shared.di.AppModule
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private lateinit var appViewModel: MviViewModel<AppMviState, AppIntentions, AppUiEvent>

private val navigationGraphs: Set<NavigationGraphBuilder> by lazy {
    setOf(
        HomepageNavigationModule.getHomepageNavigation(),
        PaymentsNavigationModule.getPaymentsNavigation(),
        ChartNavigationModule.getChartNavigation()
    )
}

private val tabNavigation: TabNavigation =  TabNavigation(navigationGraphs)

private val tabs = BottomTab.entries.toList()

@Composable
internal fun App() {

    val scope = LocalLifecycleOwner.current.lifecycleScope

    val navController = rememberNavController()

    DisposableEffect(Unit) {

        appViewModel = AppModule.getAppViewModel().apply {
            start(scope)
        }

        appViewModel.event.onEach {
            when (it) {
                is AppUiEvent.TabChanged -> {
                    navController.navigate(tabNavigation.bindWithRoute(it.activeTab)) {
                        popUpTo(navController.graph.findStartDestination().route!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }.launchIn(scope)

        onDispose {
            appViewModel.stop()
        }
    }

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