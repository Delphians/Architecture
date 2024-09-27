package com.che.architecture

import org.gradle.api.Plugin
import org.gradle.api.Project

class ArchitecturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            configureDetektPlugin()
        }
    }
}

