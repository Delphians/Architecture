package com.che.architecture.di

import com.che.architecture.features.chart.di.CHART_NAVIGATION
import com.che.architecture.features.chart.di.chartNavigationModule
import com.che.architecture.features.homepage.di.HOMEPAGE_NAVIGATION
import com.che.architecture.features.homepage.di.homepageNavigationModule
import com.che.architecture.features.payments.di.PAYMENTS_NAVIGATION
import com.che.architecture.features.payments.di.paymentsNavigationModule
import com.che.architecture.features.shared.di.appModule
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal const val GRAPH = "GRAPH"

val applicationModule = module {
    includes(
        appModule,
        chartNavigationModule,
        paymentsNavigationModule,
        homepageNavigationModule
    )

    single<Set<NavigationGraphBuilder>>(named(GRAPH)) {
        setOf(
            get(named(HOMEPAGE_NAVIGATION)),
            get(named(PAYMENTS_NAVIGATION)),
            get(named(CHART_NAVIGATION))
        )
    }
}
