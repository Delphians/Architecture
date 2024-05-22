package com.che.architecture.features.payments.di

import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityComponent::class)
abstract class PaymentsNavigationModule {

    @IntoSet
    @Binds
    internal abstract fun bindsPaymentsNavigation(it: PaymentsNavigation): NavigationGraphBuilder
}
