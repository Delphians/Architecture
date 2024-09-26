package com.che.architecture.data.remote.datasource.fakes

import com.che.architecture.domain.model.Price
import kotlinx.serialization.json.Json

internal const val PRICE_RESPONSE_STRING = """[
    {
        "date": "2012-01-03",
        "close": 127.5,
        "high": 128.38,
        "low": 127.43,
        "open": 127.76,
        "volume": 193309527,
        "adjClose": 101.9545050834,
        "adjHigh": 102.6581910792,
        "adjLow": 101.898530061,
        "adjOpen": 102.1624123094,
        "adjVolume": 193309527,
        "divCash": 0.0,
        "splitFactor": 1.0
    },
    {
        "date": "2012-01-04",
        "close": 127.7,
        "high": 127.81,
        "low": 126.71,
        "open": 127.2,
        "volume": 126705865,
        "adjClose": 102.1144337188,
        "adjHigh": 102.2023944683,
        "adjLow": 101.3227869735,
        "adjOpen": 101.7146121302,
        "adjVolume": 126705865,
        "divCash": 0.0,
        "splitFactor": 1.0
    }
]"""

internal val priceResponse: List<Price> = Json.decodeFromString<List<Price>>(PRICE_RESPONSE_STRING)
