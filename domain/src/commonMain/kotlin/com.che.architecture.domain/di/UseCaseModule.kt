package com.che.architecture.domain.di

import com.che.architecture.domain.repositories.StockPricesRepository
import com.che.architecture.domain.prices.DailyTickerPrices
import com.che.architecture.domain.prices.implementation.DailyTickerPricesImpl

object UseCaseModule {
    fun provideDailyTickerPrices(stockPricesRepository: StockPricesRepository): DailyTickerPrices =
        DailyTickerPricesImpl(stockPricesRepository = stockPricesRepository)
}
