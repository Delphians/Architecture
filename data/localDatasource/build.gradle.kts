import com.che.architecture.plugins.common.configureMultiplatform

plugins {
    id("android.architecture.plugin")
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    configureMultiplatform("data.localDatasource")
    sourceSets {
        commonMain.dependencies {
            implementation(projects.base)
            implementation(projects.domain)
            implementation(libs.datetime)
            implementation(libs.room.runtime)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }
    }
}

android {
    namespace = "com.che.architecture.data.localDatasource"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
}