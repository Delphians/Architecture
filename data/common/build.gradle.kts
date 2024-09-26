import com.che.architecture.configAndroidLibrary
import com.che.architecture.configureMultiplatform

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
   configureMultiplatform("data.common")

    sourceSets {
        commonMain.dependencies {
            implementation(projects.base)
            implementation(projects.domain)
            implementation(projects.data.remoteDatasource)
            implementation(libs.datetime)
            implementation(libs.serialization)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
            implementation(libs.ktor.ktorMockClient)
        }
    }
}

android {
    namespace = "com.che.architecture.data.common"
    configAndroidLibrary()
}