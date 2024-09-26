package com.che.architecture.data.remote.datasource.fakes

import com.che.architecture.data.remote.datasource.tiingo.TiingoUrlBuilder

internal fun mockTiingoBuilder(
    tiingoBaseUrl: String = "",
    tiingoToken: String = ""
) = TiingoUrlBuilder(tiingoBaseUrl, tiingoToken)
