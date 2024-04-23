package com.che.architecture.features.homepage.di

import com.che.architecture.features.homepage.navigation.HomepageNavigation
import com.che.architecture.features.homepage.navigation.HomepageNavigationImpl
import dagger.Binds
import dagger.Module

@Module(includes = [HomepageModule::class, HomepageProcessorsModule::class])
abstract class HomepageNavigationModule {

    @Binds
    internal abstract fun bindsHomepageNavigation(it: HomepageNavigationImpl): HomepageNavigation
}
