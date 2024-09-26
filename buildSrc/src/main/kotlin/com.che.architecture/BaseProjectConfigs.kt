package com.che.architecture

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

//internal fun Project.configureForRootProject() {
//    tasks.register("clean", Delete::class.java) {
//        delete(rootProject.layout.buildDirectory)
//    }
//}
//
//internal fun Project.configureForAllProjects() {
//    // apply and configure detekt plugin
//    configureDetektPlugin()
//
//    repositories.apply {
//        mavenCentral()
//        google()
//    }
//
//    tasks.withType<KotlinJvmCompile>().configureEach {
//        compilerOptions.apply {
//            jvmTarget.set(AndroidSdk.jvmTarget)
//            freeCompilerArgs.add(
//                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
//            )
//            freeCompilerArgs.addAll(
//                "-P",
//                "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=$rootDir/stability-config.txt"
//            )
//            if (project.findProperty("composeCompilerReports") == "true") {
//                freeCompilerArgs.addAll(
//                    "-P",
//                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_compiler_report"
//                )
//            }
//            if (project.findProperty("composeCompilerMetrics") == "true") {
//                freeCompilerArgs.addAll(
//                    "-P",
//                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.layout.buildDirectory.get()}/compose_metrics_report"
//                )
//            }
//        }
//    }
//
//    tasks.withType<Test> {
//        useJUnitPlatform {
//            includeEngines.add("junit-jupiter")
//        }
//    }
//}
//
//internal val Project.appExtension: AppExtension
//    get() = extensions.getByType(AppExtension::class.java)
//
//internal val Project.libraryExtension: LibraryExtension
//    get() = extensions.getByType(LibraryExtension::class.java)

