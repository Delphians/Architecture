import com.che.architecture.configAndroidLibrary
import com.che.architecture.configureMultiplatform

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    configureMultiplatform("base")
    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.base"
    configAndroidLibrary()
}



