package com.che.architecture.features.chart.di

import com.che.architecture.features.chart.navigation.ChartNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
abstract class ChartNavigationModule {

    @IntoSet
    @Binds
    internal abstract fun bindsChartNavigation(it: ChartNavigation): NavigationGraphBuilder
}
