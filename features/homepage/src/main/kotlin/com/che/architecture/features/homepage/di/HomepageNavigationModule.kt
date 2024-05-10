package com.che.architecture.features.homepage.di

import com.che.architecture.features.homepage.navigation.HomepageNavigationImpl
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

object HomepageNavigationModule {
     fun getHomepageNavigation(): NavigationGraphBuilder = HomepageNavigationImpl()
}
