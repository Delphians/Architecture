package com.che.architecture.di

import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.features.chart.di.chartNavigationModule
import com.che.architecture.features.chart.di.chartNavigationModuleName
import com.che.architecture.features.homepage.di.homepageNavigationModule
import com.che.architecture.features.homepage.di.homepageNavigationModuleName
import com.che.architecture.features.payments.di.paymentsNavigationModule
import com.che.architecture.features.payments.di.paymentsNavigationModuleName
import com.che.architecture.features.shared.di.appModule
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val graph: StringQualifier by StringQualifierName(named("graph"))

internal val applicationModule = module {

    includes(
        appModule,
        chartNavigationModule,
        paymentsNavigationModule,
        homepageNavigationModule
    )

    single<Set<NavigationGraphBuilder>>(graph) {
        setOf(
            get(homepageNavigationModuleName),
            get(paymentsNavigationModuleName),
            get(chartNavigationModuleName)
        )
    }

    single(named("tiingoBaseUrl")) {
        "api.tiingo.com/tiingo/"
    }

    single(named("tiingoToken")) { "" }
}
