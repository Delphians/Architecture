package com.che.architecture.data.remote.tiingo

internal fun mockTiingoBuilder(
    tiingoBaseUrl: String = "",
    tiingoToken: String = ""
) = TiingoUrlBuilder(tiingoBaseUrl, tiingoToken)
