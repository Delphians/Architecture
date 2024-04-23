package com.che.architecture.data.di

import com.che.architecture.data.remote.repositories.StockPricesRepositoryImpl
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
