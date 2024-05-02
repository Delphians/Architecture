package com.che.architecture.features.homepage.mvi.homeScreen

import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.model.Ticker

internal data class HomepageState(val ticker: Ticker = FakeStockData.fakeTicker)
