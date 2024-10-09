package com.che.architecture.domain.utils

import kotlin.reflect.KProperty

class StringQualifierName<T>(var value: T) {

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        return value
    }
}
