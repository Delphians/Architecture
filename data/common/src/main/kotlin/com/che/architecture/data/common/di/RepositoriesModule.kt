package com.che.architecture.data.common.di

import com.che.architecture.data.common.repositories.StockPricesRepositoryImpl
import com.che.architecture.domain.repositories.StockPricesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    internal abstract fun bindsStockPricesRepository(
        it: StockPricesRepositoryImpl
    ): StockPricesRepository
}
