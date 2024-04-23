package com.che.architecture

import com.android.build.gradle.BaseExtension

internal fun BaseExtension.configureCompose() {
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion =
        Versions.Androidx.Compose.compiler
}