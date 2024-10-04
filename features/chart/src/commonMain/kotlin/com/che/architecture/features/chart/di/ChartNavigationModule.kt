package com.che.architecture.features.chart.di

import com.che.architecture.features.chart.navigation.ChartNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CHART_NAVIGATION = "ChartNavigation"

val chartNavigationModule = module {
    single<NavigationGraphBuilder>(named(CHART_NAVIGATION)) {
        ChartNavigation()
    }
}
