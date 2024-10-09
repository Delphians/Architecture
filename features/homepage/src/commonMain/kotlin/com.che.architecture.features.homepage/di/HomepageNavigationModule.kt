package com.che.architecture.features.homepage.di

import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homepageNavigationModuleName: StringQualifier
        by StringQualifierName(named("HomepageNavigation"))

val homepageNavigationModule = module {
    includes(homepageModule)
    single<NavigationGraphBuilder>(homepageNavigationModuleName) {
        HomepageNavigation()
    }
}
