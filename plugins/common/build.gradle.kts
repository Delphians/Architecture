plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation("com.android.tools.build:gradle:8.4.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.20")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.7")
}

gradlePlugin {
    plugins {
        register("android.architecture.plugin") {
            id = "android.architecture.plugin"
            implementationClass = "com.che.architecture.plugins.common.ArchitecturePlugin"
        }
    }
}

