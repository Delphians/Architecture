package com.che.architecture.data.common.di

import com.che.architecture.data.common.repositories.StockPricesRepositoryImpl
import com.che.architecture.data.remote.datasource.di.remoteDataSourceModule
import com.che.architecture.domain.repositories.StockPricesRepository
import org.koin.dsl.module

val repositoriesModule = module {

    includes(remoteDataSourceModule)

    single<StockPricesRepository> {
        StockPricesRepositoryImpl(
            errorDispatcher = get(),
            tiingoDataSource = get()
        )
    }
}
