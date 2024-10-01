package com.che.architecture.features.payments.mvi.processor

import com.che.architecture.base.mvi.interfaces.IntentionProcessor
import com.che.architecture.base.mvi.interfaces.MviResult
import com.che.architecture.domain.fakes.FakeStockData
import com.che.architecture.domain.fakes.FakeStockData.FAKE_DELAY
import com.che.architecture.domain.fakes.FakeStockData.fakeEndDate
import com.che.architecture.domain.fakes.FakeStockData.fakeStartDate
import com.che.architecture.domain.prices.DailyTickerPrices
import com.che.architecture.features.payments.mvi.DailyPriceResults
import com.che.architecture.features.payments.mvi.EmptyResults
import com.che.architecture.features.payments.mvi.LoadingResults
import com.che.architecture.features.payments.mvi.PaymentsIntention
import com.che.architecture.features.payments.mvi.PaymentsState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform

internal class GetTickerPriceIntentionProcessor(
    private val dailyTickerPrices: DailyTickerPrices
) : IntentionProcessor<PaymentsState, PaymentsIntention> {

    override fun process(intentions: Flow<PaymentsIntention>): Flow<MviResult<PaymentsState>> =
        intentions.filterIsInstance<PaymentsIntention.GetTickerPriceIntention>()
            .transform {
                emit(LoadingResults)

                delay(FAKE_DELAY)

                // To get the fake data
                var prices = FakeStockData
                    .fakePricesGenerator(fakeStartDate..fakeEndDate)
                    .toPersistentList()

                // To get the real data from Tiingo
                if (prices.isEmpty()) {
                    prices = dailyTickerPrices(it.ticker, it.dateRange).toPersistentList()
                }

                emit(
                    if (prices.isNotEmpty()) {
                        DailyPriceResults(prices = prices)
                    } else {
                        EmptyResults
                    }
                )
            }
}
