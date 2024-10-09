package com.che.architecture.features.payments.di

import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val paymentsNavigationModuleName: StringQualifier
        by StringQualifierName(named("PaymentsNavigation"))

val paymentsNavigationModule = module {
    includes(paymentsModule)
    single<NavigationGraphBuilder>(paymentsNavigationModuleName) {
        PaymentsNavigation()
    }
}
