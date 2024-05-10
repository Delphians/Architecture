package com.che.architecture.features.chart.di

import com.che.architecture.features.chart.navigation.ChartNavigationImpl
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

object ChartNavigationModule {
    fun getChartNavigation(): NavigationGraphBuilder = ChartNavigationImpl()
}
