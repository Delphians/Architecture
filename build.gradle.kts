buildscript {
    apply(from = "buildSrc/buildDependencies.gradle")
    val plugins: Map<Any, Any> by extra
    val versions: Map<Any, String> by extra

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(plugins.getValue("androidGradlePlugin"))
        classpath(plugins.getValue("kotlinGradlePlugin"))
        classpath(plugins.getValue("composeCompilerPlugin"))
        classpath(kotlin("serialization", version = versions.getValue("kotlin")))
        classpath(com.che.architecture.Libraries.Hilt.gradlePlugin)
    }
}
