import com.che.architecture.plugins.common.configureMultiplatform

plugins {
    id("android.architecture.plugin")
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    configureMultiplatform("atomic.design")
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.che.architecture.atomic.design.resources"
    generateResClass = always
}

android {
    namespace = "com.che.architecture.atomic.design"
    buildFeatures {
        compose = true
    }
}

