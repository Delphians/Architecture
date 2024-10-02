import com.che.architecture.plugins.common.configureMultiplatform
import com.che.architecture.plugins.common.handleProductFlavour

plugins {
    id("android.architecture.plugin")
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
            implementation(projects.atomicDesign)
            implementation(projects.base)
            implementation(projects.domain)
            implementation(projects.data.common)
            implementation(projects.features.homepage)
            implementation(projects.features.shared)
            implementation(projects.features.payments)
            implementation(projects.features.chart)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.compose.navigation)
            implementation(libs.androidx.lifecycle.compose)

            api(libs.koin.core)
            implementation(libs.koin.compose)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.che.architecture.app"

    handleProductFlavour()

    buildFeatures { buildConfig = true }
}