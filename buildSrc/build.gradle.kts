apply(from = "buildDependencies.gradle")
val plugins: Map<Any, Any> by extra
val libs: Map<Any,Any> by extra

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(plugins.getValue("kotlinGradlePlugin"))
    implementation(plugins.getValue("androidGradlePlugin"))
    implementation(plugins.getValue("detektGradlePlugin"))
    implementation(plugins.getValue("composeCompilerPlugin"))
}

