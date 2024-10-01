package com.che.architecture.plugins.common

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class ArchitecturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureForAllProjects()

            if (this == rootProject) {
                configureForRootProject()
            }
            // apply baseline configurations based on plugins applied
            plugins.all {
                when (this) {
                    is AppPlugin -> {
                        appExtension.configAndroidCommon(project)
                    }

                    is LibraryPlugin -> {
                        libraryExtension.configAndroidCommon(project)
                    }
                }
            }
        }
    }
}

