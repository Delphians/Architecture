import com.che.architecture.plugins.common.configureMultiplatform

plugins {
    id("android.architecture.plugin")
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-parcelize")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    configureMultiplatform("features.shared")
    sourceSets {
        commonMain.dependencies {
            implementation(projects.atomicDesign)
            implementation(projects.base)
            implementation(projects.domain)

            implementation(compose.ui)
            implementation(libs.compose.navigation)

            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.features.shared"
}
