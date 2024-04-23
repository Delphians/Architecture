package com.che.architecture

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun Project.configureKapt() {
    extensions.configure(KaptExtension::class.java) {
        correctErrorTypes = true

        javacOptions {
            option("-Adagger.hilt.disableModulesHaveInstallInCheck=true")
        }
    }
}