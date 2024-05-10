package com.che.architecture.data.remote.tiingo

import com.che.architecture.data.remote.tiingo.ProvidedNames.TIINGO_FORMAT
import com.che.architecture.data.remote.tiingo.ProvidedNames.TIINGO_TOKEN
import com.che.architecture.data.remote.tiingo.TiingoPathParams.Format.JSON
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.path

internal class TiingoUrlBuilder(
    private val tiingoBaseUrl: String,
    private val tiingoToken: String
) {

    private val url = URLBuilder().apply {
        host = tiingoBaseUrl
        protocol = URLProtocol.HTTPS
    }

    fun addPathParams(vararg path: String) {
        if (path.isEmpty()) {
            return
        }
        url.path(*path)
    }

    fun addQueryParams(params: Map<String, String>) {
        if (params.isEmpty()) {
            return
        }

        url.apply {
            params.forEach {
                parameters.append(it.key, it.value)
            }
        }
    }

    fun build() = url.apply {
        parameters.append(TIINGO_TOKEN, tiingoToken)
        parameters.append(TIINGO_FORMAT, JSON)
    }.toString()
}

internal object ProvidedNames {
    const val TIINGO_BASE_URL = "tiingo"
    const val TIINGO_TOKEN = "token"
    const val TIINGO_FORMAT = "format"
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
