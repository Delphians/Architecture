package com.che.architecture.domain.fakes

import com.che.architecture.domain.model.Price
import com.che.architecture.domain.model.Ticker
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.random.Random

@Suppress("MagicNumber")
object FakeStockData {

    const val FAKE_TIINGO_URL = "api.tiingo.com/tiingo"
    const val FAKE_TOKEN = "ASD123FG"
    val fakeClosePricePoints = persistentListOf(10.0, 30.0, 50.0, 20.0, 60.0, 40.0, 30.0)
    val fakeStartDate: LocalDate = LocalDate(2012, 1, 1)
    val fakeEndDate: LocalDate = LocalDate(2013, 1, 2)
    val fakeTicker = Ticker("GOOG")

    // Take the dateRange from Price.json file
    val dateRange: ClosedRange<LocalDate> =
        LocalDate(2012, 1, 3)..LocalDate(2012, 1, 4)

    const val FAKE_DELAY: Long = 2000
    private const val FAKE_ZERO = 0.0

    fun fakePricesGenerator(rangeOfDates: ClosedRange<LocalDate>): List<Price> {
        val prices = mutableListOf<Price>()
        val startPrice = 100.0
        val endPrice = startPrice + 15
        var startDate = rangeOfDates.start

        while (startDate in rangeOfDates) {
            prices.add(
                Price(
                    date = startDate.toString(),
                    close = Random.nextDouble(startPrice, endPrice),
                    high = Random.nextDouble(startPrice, endPrice),
                    low = Random.nextDouble(startPrice, endPrice),
                    open = Random.nextDouble(startPrice, endPrice),
                    volume = Random.nextLong(10000, 20000),
                    adjClose = FAKE_ZERO,
                    adjHigh = FAKE_ZERO,
                    adjLow = FAKE_ZERO,
                    adjOpen = FAKE_ZERO,
                    divCash = FAKE_ZERO,
                    adjVolume = FAKE_ZERO.toLong(),
                    splitFactor = FAKE_ZERO
                )
            )
            startDate = startDate.plus(1, DateTimeUnit.DAY)
        }

        return prices
    }

    fun countOfDaysInRange(range: ClosedRange<LocalDate>): Int {
        var startDate = range.start
        var count = 0

        while (startDate in range) {
            count++
            startDate = startDate.plus(1, DateTimeUnit.DAY)
        }

        return count
    }
}
