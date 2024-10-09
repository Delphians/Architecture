package com.che.architecture.features.chart.di

import com.che.architecture.domain.utils.StringQualifierName
import com.che.architecture.features.chart.navigation.ChartNavigation
import com.che.architecture.features.shared.navigation.NavigationGraphBuilder
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

val chartNavigationModuleName: StringQualifier
        by StringQualifierName(named("ChartNavigation"))

val chartNavigationModule = module {
    includes(chartModule)
    single<NavigationGraphBuilder>(chartNavigationModuleName) {
        ChartNavigation()
    }
}
