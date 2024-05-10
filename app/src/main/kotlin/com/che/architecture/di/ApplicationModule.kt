package com.che.architecture.di

import com.che.architecture.BuildConfig
import com.che.architecture.data.di.RepositoriesModule.provideStockPricesRepository
import com.che.architecture.features.payments.di.PaymentsModule.paymentsModuleInjection

internal object ApplicationModule {
    fun buildPaymentsModule() {
        paymentsModuleInjection(
            provideStockPricesRepository(
                BuildConfig.tiingoBaseUrl,
                BuildConfig.tiingoToken
            )
        )
    }
}
