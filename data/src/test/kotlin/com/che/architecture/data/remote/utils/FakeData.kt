package com.che.architecture.data.remote.utils

import com.che.architecture.data.remote.tiingo.mockTiingoBuilder

internal object FakeData {
    private val content = readDataFromFile("response/Price.json")

    val engine = createMockEngine(content)
    val fakeUrlBuilder = mockTiingoBuilder()
}
