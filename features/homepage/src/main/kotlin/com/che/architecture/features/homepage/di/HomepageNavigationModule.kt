package com.che.architecture.features.homepage.di

import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
abstract class HomepageNavigationModule {

    @IntoSet
    @Binds
    internal abstract fun bindsHomepageNavigation(it: HomepageNavigation): NavigationGraphBuilder
}
