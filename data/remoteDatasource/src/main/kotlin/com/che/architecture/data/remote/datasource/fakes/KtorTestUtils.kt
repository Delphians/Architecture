package com.che.architecture.data.remote.datasource.fakes

import java.io.File
import java.nio.file.Paths

internal fun readDataFromFile(path: String): ByteArray {
    val paths = Paths.get("src", "test", "kotlin")
    val absolutePath = "${paths.toFile().getAbsolutePath()}/$path"
    return File(absolutePath).inputStream().readBytes()
}
