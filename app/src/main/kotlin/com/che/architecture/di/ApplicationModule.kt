package com.che.architecture.di

import com.che.architecture.BuildConfig
import com.che.architecture.data.remote.datasource.ProvidedNames
import com.che.architecture.data.remote.datasource.di.KtorModule
import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.domain.di.UseCaseModule
import com.che.architecture.data.common.di.RepositoriesModule
import com.che.architecture.data.remote.datasource.di.RemoteDataSourceModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module(
    includes = [
        KtorModule::class,
        RepositoriesModule::class,
        RemoteDataSourceModule::class,
        UseCaseModule::class,
        ErrorDomainModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Named(ProvidedNames.TIINGO_BASE_URL)
    @Provides
    fun provideTiingoBaseUrl() = BuildConfig.tiingoBaseUrl

    @Named(ProvidedNames.TIINGO_TOKEN)
    @Provides
    fun provedTiingoToken() = BuildConfig.tiingoToken
}
