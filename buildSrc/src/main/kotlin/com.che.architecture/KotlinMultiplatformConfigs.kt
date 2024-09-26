package com.che.architecture

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.configureMultiplatform(name: String) {
    androidTarget {
        compilerOptions.apply {
            jvmTarget.set(AndroidSdk.jvmTarget)
        }
    }
    listOf(
        iosArm64(), //real iPhone
        iosSimulatorArm64() //iOS simulator on macOS with Apple Silicon M1
    ).forEach {
        it.binaries.framework {
            baseName = name
            binaryOption("bundleId", "com.che.architecture"+name)
        }
    }
}

