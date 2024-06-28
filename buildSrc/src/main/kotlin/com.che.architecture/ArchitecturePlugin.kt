package com.che.architecture

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin

/**
 * A Plugin that is responsible to set baseline settings for any
 * - Android Project
 * - Kotlin JVM Project
 * - Java JVM Project
 */
class ArchitecturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureForAllProjects()

            if (this == rootProject) {
                configureForRootProject()
            }

            // apply baseline configurations based on plugins applied
            plugins.all {
                when (this) {
                    is AppPlugin -> {
                        appExtension.configureCommonSdkOptions(project)
                    }
                    is LibraryPlugin -> {
                        libraryExtension.configureCommonSdkOptions(project)
                    }

                    is Kapt3GradleSubplugin -> {
                        configureKapt()
                    }
                }
            }
        }
    }
}

internal object AndroidSdk {
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val buildTools = "$compileSdk.0.0"
    val javaVersion = JavaVersion.VERSION_19
}