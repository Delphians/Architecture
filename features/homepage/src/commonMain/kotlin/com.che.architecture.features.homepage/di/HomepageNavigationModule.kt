package com.che.architecture.features.homepage.di

import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

object HomepageNavigationModule {
    fun getHomepageNavigation(): NavigationGraphBuilder = HomepageNavigation()
}
