package com.che.architecture.di

import com.che.architecture.data.common.di.RepositoriesModule
import com.che.architecture.domain.di.UseCaseModule
import com.che.architecture.features.payments.di.PaymentsModule.paymentsModuleInjection

internal object ApplicationModule {

    fun buildPaymentsModule() {
        paymentsModuleInjection(
            UseCaseModule.provideDailyTickerPrices(
                RepositoriesModule.provideStockPricesRepository(
                    """api.tiingo.com/tiingo/""",
                    ""
                )
            )
        )
    }
}
