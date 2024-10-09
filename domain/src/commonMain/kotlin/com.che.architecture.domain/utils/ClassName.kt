package com.che.architecture.domain.utils

import kotlin.reflect.KClass

fun <T : Any> KClass<T>.className(): String = simpleName ?: toString()
