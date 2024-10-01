package com.che.architecture.data.remote.datasource.tiingo

import com.che.architecture.data.remote.datasource.ProvidedNames.TIINGO_FORMAT
import com.che.architecture.data.remote.datasource.ProvidedNames.TIINGO_TOKEN
import com.che.architecture.data.remote.datasource.TiingoPathParams.Format.JSON
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
