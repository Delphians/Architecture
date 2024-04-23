package com.che.architecture.domain.di

import com.che.architecture.domain.usecase.prices.DailyTickerPrices
import com.che.architecture.domain.usecase.prices.implementation.DailyTickerPricesImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    internal abstract fun bindsDailyTickerPrices(impl: DailyTickerPricesImpl): DailyTickerPrices
}
