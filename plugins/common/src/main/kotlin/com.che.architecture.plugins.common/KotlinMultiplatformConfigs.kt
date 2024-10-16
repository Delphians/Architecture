package com.che.architecture.plugins.common

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.configureMultiplatform(name: String) {
    androidTarget {
        compilerOptions.apply {
            jvmTarget.set(AndroidSdk.jvmTarget)
        }
    }
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
    listOf(
        iosArm64(), //real iPhone
        iosSimulatorArm64() //iOS simulator on macOS with Apple Silicon M1
    ).forEach {
        it.binaries.framework {
            baseName = name
            binaryOption("bundleId", "com.che.architecture" + name)
        }
    }
}

