package com.che.architecture.domain.di

import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.usecase.prices.DailyTickerPrices
import com.che.architecture.domain.usecase.prices.implementation.DailyTickerPricesImpl

object UseCaseModule {
    fun provideDailyTickerPrices(stockPricesRepository: StockPricesRepository): DailyTickerPrices =
        DailyTickerPricesImpl(stockPricesRepository = stockPricesRepository)
}
