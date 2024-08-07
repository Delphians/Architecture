package com.che.architecture.data.remote.datasource.fakes

internal object FakeData {
    private val content = readDataFromFile("com/che/architecture/data/remote/datasource/response/Price.json")
    val engine = createMockEngine(content)
    val fakeUrlBuilder = mockTiingoBuilder()
}
