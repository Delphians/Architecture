import com.che.architecture.configAndroidLibrary
import com.che.architecture.configureMultiplatform

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}


kotlin {
    configureMultiplatform("domain")
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base)
            implementation(libs.coroutines.core)
            implementation(libs.datetime)
            implementation(libs.immutable.collections)
            implementation(libs.serialization)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.domain"
    configAndroidLibrary()
}

