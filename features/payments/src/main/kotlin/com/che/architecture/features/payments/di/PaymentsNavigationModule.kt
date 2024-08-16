package com.che.architecture.features.payments.di

import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.features.payments.navigation.PaymentsNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

object PaymentsNavigationModule {
    fun getPaymentsNavigation(): NavigationGraphBuilder = PaymentsNavigation(
        ErrorDomainModule.provideEventListener()
    )
}
