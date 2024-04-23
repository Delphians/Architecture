package com.che.architecture

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

internal fun BaseExtension.configureCommonSdkOptions(project: Project) {
    setCompileSdkVersion(AndroidSdk.compileSdk)
    buildToolsVersion(AndroidSdk.buildTools)

    // Enable view binding for all.
    buildFeatures.viewBinding = true

    //Compose
    configureCompose()

    defaultConfig {
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.javaVersion
        targetCompatibility = AndroidSdk.javaVersion
        isCoreLibraryDesugaringEnabled = true
    }

    project.dependencies.add("coreLibraryDesugaring", Libraries.desugaring)

    testOptions {
        unitTests.all { test ->
            if (test.name.contains("Release")) {
                test.isEnabled = false
                return@all
            }
            test.useJUnitPlatform {
                includeEngines.addAll(listOf("junit-jupiter"))
            }
        }
        unitTests.isIncludeAndroidResources = true
    }
}

fun BaseExtension.handleProductFlavour() {
    flavorDimensions(FlavorDimensions.FIRST_DIMENSION)
    productFlavors {
        register(ProductFlavors.STAGING) {
            applicationIdSuffix = ".${ProductFlavors.STAGING}"

            buildConfigField(
                "String", EnvironmentUrls.TIINGO_BASE_URL, "\"api.tiingo.com/tiingo/\""
            )

            buildConfigField(
                "String", ApiTokens.TIINGO, "\"\""
            )
        }
        register(ProductFlavors.PRODUCTION) {
            buildConfigField(
                "String", EnvironmentUrls.TIINGO_BASE_URL, "\"api.tiingo.com/tiingo/\""
            )

            buildConfigField(
                "String", ApiTokens.TIINGO, "\"\""
            )
        }
    }
}
