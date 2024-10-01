import com.che.architecture.plugins.common.configureMultiplatform

plugins {
    id("android.architecture.plugin")
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    configureMultiplatform("data.remoteDatasource")
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base)
            implementation(projects.domain)
            implementation(libs.datetime)
            implementation(libs.ktor.ktorMockClient)
            implementation(libs.ktor.core)
            implementation(libs.ktor.cio)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.room.runtime)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.data.remoteDatasource"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}