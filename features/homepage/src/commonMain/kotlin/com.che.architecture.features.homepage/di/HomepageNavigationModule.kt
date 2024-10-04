package com.che.architecture.features.homepage.di

import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val HOMEPAGE_NAVIGATION = "HomepageNavigation"

val homepageNavigationModule = module {
    single<NavigationGraphBuilder>(named(HOMEPAGE_NAVIGATION)) {
        HomepageNavigation()
    }
}
