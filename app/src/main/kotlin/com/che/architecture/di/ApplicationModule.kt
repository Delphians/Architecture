package com.che.architecture.di

import com.che.architecture.BuildConfig
import com.che.architecture.data.di.KtorModule
import com.che.architecture.data.di.RepositoriesModule
import com.che.architecture.data.remote.tiingo.ProvidedNames
import com.che.architecture.domain.di.ErrorDomainModule
import com.che.architecture.domain.di.UseCaseModule
import com.che.architecture.features.chart.di.ChartNavigationModule
import com.che.architecture.features.homepage.di.HomepageNavigationModule
import com.che.architecture.features.payments.di.PaymentsNavigationModule
import com.che.architecture.features.shared.di.AppMviModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module(
    includes = [
        AppMviModule::class,
        KtorModule::class,
        RepositoriesModule::class,
        UseCaseModule::class,
        PaymentsNavigationModule::class,
        ChartNavigationModule::class,
        HomepageNavigationModule::class,
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
