package com.che.architecture.features.payments.di

import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.features.payments.navigation.PaymentsNavigationImpl
import dagger.Binds
import dagger.Module

@Module(includes = [PaymentsModule::class, PaymentsProcessorsModule::class])
abstract class PaymentsNavigationModule {

    @Binds
    internal abstract fun bindsPaymentsNavigation(it: PaymentsNavigationImpl): PaymentsNavigation
}
