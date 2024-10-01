package com.che.architecture.plugins.common

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object AndroidSdk {
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val buildTools = "$compileSdk.0.0"
    val javaVersion = JavaVersion.VERSION_21
    val jvmTarget = JvmTarget.JVM_21
}
