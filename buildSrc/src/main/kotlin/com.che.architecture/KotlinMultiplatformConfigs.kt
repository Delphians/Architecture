package com.che.architecture

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


fun KotlinMultiplatformExtension.configureMultiplatform() {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = AndroidSdk.javaVersion.toString()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
}