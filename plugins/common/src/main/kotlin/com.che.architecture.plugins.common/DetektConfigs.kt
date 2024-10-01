package com.che.architecture.plugins.common

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.DetektPlugin.Companion.DETEKT_EXTENSION
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

/**
 * Apply detekt configs to the [Project].
 */
internal fun Project.configureDetektPlugin() {

    // apply detekt plugin
    pluginManager.apply(DetektPlugin::class.java)

    // enable Ktlint formatting
    dependencies.add(
        "detektPlugins",
        "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7"
    )

    tasks.withType<Detekt> {
        jvmTarget = AndroidSdk.javaVersion.toString()
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom(files("${project.rootDir}/detekt.yml"))
        reports {
            html.outputLocation.set(
                file("${project.layout.buildDirectory.get()}/reports/detekt/${project.name}.html")
            )
        }
    }

    val extension =
        project.extensions.findByType(DetektExtension::class.java) ?: project.extensions.create(
            DETEKT_EXTENSION,
            DetektExtension::class.java
        )

    extension.source = files(
        "src/commonMain/kotlin",
        "src/commonTest/kotlin",
        "src/androidMain/kotlin",
        "src/iosMain/kotlin",
        "src/androidUnitTest/kotlin",
        "src/iosTest/kotlin"
    )

}