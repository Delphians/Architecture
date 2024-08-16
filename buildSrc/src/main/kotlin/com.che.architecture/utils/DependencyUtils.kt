package com.che.architecture.utils

import com.che.architecture.Libraries
import com.che.architecture.Versions
import org.gradle.api.artifacts.dsl.DependencyHandler

enum class ConfigurationName(val value: String) {
    IMPLEMENTATION("implementation"),
    KAPT("kapt"),
    TEST_IMPLEMENTATION("testImplementation"),
    ANDROID_TEST_IMPLEMENTATION("androidTestImplementation"),
    KAPT_ANDROID_TEST("kaptAndroidTest"),
    TEST_COMPILE_ONLY("testCompileOnly"),
    TEST_RUNTIME_ONLY("testRuntimeOnly")
}

fun DependencyHandler.add(
    configurationName: ConfigurationName,
    vararg dependencies: Any
) = apply {
    dependencies.forEach {
        add(configurationName.value, it)
    }
}

fun DependencyHandler.useCompose(
    isFullPackage: Boolean = false,
    isNavigation: Boolean = false
) {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        Libraries.Androidx.Compose.runtime
    )

    if (isNavigation) {
        add(
            configurationName = ConfigurationName.IMPLEMENTATION,
            Libraries.Androidx.Compose.navigation
        )
    }

    if (isFullPackage) {
        add(
            configurationName = ConfigurationName.IMPLEMENTATION,
            Libraries.Androidx.Compose.ui,
            Libraries.Androidx.Compose.foundation,
            Libraries.Androidx.Compose.material,
            Libraries.Androidx.Compose.uiTooling
        )
    }
}

fun DependencyHandler.useJUnitPlatform() {
    add(
        configurationName = ConfigurationName.TEST_RUNTIME_ONLY,
        "org.junit.jupiter:junit-jupiter-engine:${Versions.junitPlatform}"
    )
    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        "org.junit.jupiter:junit-jupiter-params:${Versions.junitPlatform}",
        "org.junit.jupiter:junit-jupiter-api:${Versions.junitPlatform}"
    )
}
