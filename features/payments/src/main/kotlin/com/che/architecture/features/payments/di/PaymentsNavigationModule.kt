package com.che.architecture.features.payments.di

import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.features.payments.navigation.PaymentsNavigationImpl
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder

object PaymentsNavigationModule {
    fun getPaymentsNavigation(): NavigationGraphBuilder = PaymentsNavigationImpl(
        ErrorDomainModule.provideEventListener()
    )
}
