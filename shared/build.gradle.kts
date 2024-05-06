import com.che.architecture.configureMultiplatform

plugins {
    id("architecture-plugin")
    id("kotlin-multiplatform")
    id("com.android.library")
}

kotlin {
    configureMultiplatform()
    sourceSets {}
}

android {
    namespace = "com.che.architecture.shared"
}
