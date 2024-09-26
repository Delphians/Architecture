import com.che.architecture.configAndroidLibrary
import com.che.architecture.configureMultiplatform

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    configureMultiplatform("app")
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.atomicDesign)
            implementation(projects.base)
            implementation(projects.domain)
            implementation(projects.data.common)
            implementation(projects.features.homepage)
            implementation(projects.features.shared)
            implementation(projects.features.payments)
            implementation(projects.features.chart)
            implementation(libs.compose.navigation)
            implementation(libs.androidx.lifecycle.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.che.architecture.app"
    configAndroidLibrary()
}
