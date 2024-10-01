package com.che.architecture.data.remote.datasource

object ProvidedNames {
    const val TIINGO_BASE_URL = "com/che/architecture/data/remote/datasource/tiingo"
    const val TIINGO_TOKEN = "token"
    const val TIINGO_FORMAT = "format"
}

object RemoteClient {
    const val KTOR_CLIENT = " ktor_client"
}

internal object TiingoPathParams {
    object DateTime {
        const val DAILY = "daily"
        const val START_DATE = "startDate"
        const val END_DATE = "endDate"
    }

    object Format {
        const val JSON = "json"
    }

    const val PRICES = "prices"
}
