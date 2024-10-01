package com.che.architecture.data.remote.datasource.call

import com.che.architecture.domain.model.Failure
import com.che.architecture.domain.model.Result
import com.che.architecture.domain.model.Success
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
