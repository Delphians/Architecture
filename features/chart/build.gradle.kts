import com.che.architecture.configAndroidLibrary
import com.che.architecture.configureMultiplatform

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    configureMultiplatform("features.chart")
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base)
            implementation(projects.domain)
            implementation(projects.atomicDesign)
            implementation(projects.features.shared)
            implementation(libs.coroutines.core)
            implementation(libs.compose.navigation)
            implementation(libs.androidx.lifecycle.compose)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.immutable.collections)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.features.chart"
    configAndroidLibrary()
    buildFeatures {
        compose = true
    }
}

