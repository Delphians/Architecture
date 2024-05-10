package com.che.architecture.domain.di

import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.usecase.prices.DailyTickerPricesUseCase
import com.che.architecture.domain.usecase.prices.implementation.DailyTickerPricesUseCaseImpl

object UseCaseModule {

    private var dailyTickerPricesUseCase: DailyTickerPricesUseCase? = null

    fun provideDailyTickerPricesUseCase(stockPricesRepository: StockPricesRepository): DailyTickerPricesUseCase {
        if (dailyTickerPricesUseCase == null) {
            dailyTickerPricesUseCase = DailyTickerPricesUseCaseImpl(stockPricesRepository)
        }
        return dailyTickerPricesUseCase as DailyTickerPricesUseCase
    }
}
