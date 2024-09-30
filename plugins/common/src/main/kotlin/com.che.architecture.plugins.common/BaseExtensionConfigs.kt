package com.che.architecture.plugins.common

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

internal fun BaseExtension.configAndroidCommon(project: Project) {
    setCompileSdkVersion(AndroidSdk.compileSdk)
    buildToolsVersion(AndroidSdk.buildTools)

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.javaVersion
        targetCompatibility = AndroidSdk.javaVersion
    }

    testOptions {
        unitTests.all { test ->
            if (test.name.contains("Release")) {
                test.isEnabled = false
                return@all
            }
        }
    }
}

fun BaseExtension.handleProductFlavour() {
    flavorDimensions(FlavorDimensions.FIRST_DIMENSION)
    productFlavors {
        register(ProductFlavors.STAGING) {
            applicationIdSuffix = ".${ProductFlavors.STAGING}"

            buildConfigField(
                "String", EnvironmentUrls.TIINGO_BASE_URL, """api.tiingo.com/tiingo/"""
            )

            buildConfigField(
                "String", ApiTokens.TIINGO, ""
            )
        }
        register(ProductFlavors.PRODUCTION) {
            buildConfigField(
                "String", EnvironmentUrls.TIINGO_BASE_URL, """api.tiingo.com/tiingo/"""
            )

            buildConfigField(
                "String", ApiTokens.TIINGO, ""
            )
        }
    }
}
