plugins {
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        val kotlinVersion = libs.versions.kotlin.get()
        classpath("com.android.tools.build:gradle:${libs.versions.agp.get()}")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}
