package com.che.architecture.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal suspend inline fun <reified V> HttpClient.getSafeQuery(
    url: String
): Result<V, Throwable> = runCatching {
    val response = get(url).body() as V
    Success(response)
}.getOrElse {
    Failure(it)
}
