package com.che.architecture

import com.android.build.gradle.BaseExtension
import com.che.architecture.AndroidSdk.compileSdk
import org.gradle.api.Project
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin

//internal fun BaseExtension.configureCommonSdkOptions(project: Project) {
//    setCompileSdkVersion(AndroidSdk.compileSdk)
//    buildToolsVersion(AndroidSdk.buildTools)
//
//    // Enable view binding for all.
//    buildFeatures.viewBinding = true
//
//    defaultConfig {
//        minSdk = AndroidSdk.minSdk
//        targetSdk = AndroidSdk.targetSdk
//    }
//
//    compileOptions {
//        sourceCompatibility = AndroidSdk.javaVersion
//        targetCompatibility = AndroidSdk.javaVersion
//        isCoreLibraryDesugaringEnabled = true
//    }
//
//   // project.dependencies.add("coreLibraryDesugaring", Libraries.desugaring)
//
//    testOptions {
//        unitTests.all { test ->
//            if (test.name.contains("Release")) {
//                test.isEnabled = false
//                return@all
//            }
//            test.useJUnitPlatform {
//                includeEngines.addAll(listOf("junit-jupiter"))
//            }
//        }
//        unitTests.isIncludeAndroidResources = true
//    }
//}
//
//fun BaseExtension.handleProductFlavour() {
//    flavorDimensions(FlavorDimensions.FIRST_DIMENSION)
//    productFlavors {
//        register(ProductFlavors.STAGING) {
//            applicationIdSuffix = ".${ProductFlavors.STAGING}"
//
//            buildConfigField(
//                "String", EnvironmentUrls.TIINGO_BASE_URL, """api.tiingo.com/tiingo/"""
//            )
//
//            buildConfigField(
//                "String", ApiTokens.TIINGO, ""
//            )
//        }
//        register(ProductFlavors.PRODUCTION) {
//            buildConfigField(
//                "String", EnvironmentUrls.TIINGO_BASE_URL, """api.tiingo.com/tiingo/"""
//            )
//
//            buildConfigField(
//                "String", ApiTokens.TIINGO, ""
//            )
//        }
//    }
//}

fun BaseExtension.configAndroidLibrary() {
    setCompileSdkVersion(compileSdk)
    buildToolsVersion(AndroidSdk.buildTools)

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.javaVersion
        targetCompatibility = AndroidSdk.javaVersion
    }
}

internal object AndroidSdk {
    const val minSdk = 24
    const val targetSdk = 34
    const val compileSdk = 34
    const val buildTools = "$compileSdk.0.0"
    val javaVersion = JavaVersion.VERSION_21
    val jvmTarget = JvmTarget.JVM_21
}
