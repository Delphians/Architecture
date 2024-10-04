package com.che.architecture.domain.di

import com.che.architecture.domain.prices.DailyTickerPrices
import com.che.architecture.domain.prices.implementation.DailyTickerPricesImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<DailyTickerPrices> {
        DailyTickerPricesImpl(get())
    }
}
