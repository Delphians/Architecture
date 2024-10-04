package com.che.architecture.features.payments.di

import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PAYMENTS_NAVIGATION = "PaymentsNavigation"

val paymentsNavigationModule = module {
    single<NavigationGraphBuilder>(named(PAYMENTS_NAVIGATION)) {
        PaymentsNavigation()
    }
}
