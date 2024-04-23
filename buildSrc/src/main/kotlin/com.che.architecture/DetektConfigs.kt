package com.che.architecture

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
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
        "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    )


    tasks.withType<Detekt> {

        jvmTarget = AndroidSdk.javaVersion.toString()
        buildUponDefaultConfig = true
        autoCorrect = true
        config.setFrom(files("${project.rootDir}/detekt.yml"))
        reports {
            html.outputLocation.set(
                file("${project.buildDir}/reports/detekt/${project.name}.html")
            )
        }
    }
}