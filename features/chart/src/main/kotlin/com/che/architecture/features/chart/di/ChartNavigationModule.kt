package com.che.architecture.features.chart.di

import com.che.architecture.features.chart.navigation.ChartNavigation
import com.che.architecture.features.chart.navigation.ChartNavigationImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ChartModule::class, ChartProcessorsModule::class])
abstract class ChartNavigationModule {

    @Binds
    internal abstract fun bindsChartNavigation(it: ChartNavigationImpl): ChartNavigation
}
