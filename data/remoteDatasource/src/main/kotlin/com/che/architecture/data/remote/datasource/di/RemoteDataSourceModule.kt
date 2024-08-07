package com.che.architecture.data.remote.datasource.di

import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSource
import com.che.architecture.data.remote.datasource.tiingo.TiingoDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteDataSourceModule {

    @Binds
    internal abstract fun bindsTiingoDataSource(
        it: TiingoDataSourceImpl
    ): TiingoDataSource
}
