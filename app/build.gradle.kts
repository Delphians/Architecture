import com.che.architecture.Libraries
import com.che.architecture.Versions.appVersion
import com.che.architecture.handleProductFlavour
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useCompose
import com.che.architecture.utils.useDagger

plugins {
    id("architecture-plugin")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.owasp.dependencycheck") version "7.1.2"
}

android {
    namespace = "com.che.architecture"

    defaultConfig {
        applicationId = "com.che.architecture"
        versionCode = appVersion.replace(".", "").toInt()
        versionName = appVersion
    }

    buildFeatures { buildConfig = true }

    handleProductFlavour()

    dependencyCheck {
        failBuildOnCVSS = 0F

        fun String.startsWithAny(
            vararg prefixCollection: String,
            ignoreCase: Boolean = false
        ): Boolean {
            prefixCollection.forEach {
                if (this.startsWith(it, ignoreCase))
                    return true
            }
            return false
        }

        scanConfigurations = configurations.filter {
            !it.name.startsWithAny("androidTest", "test", "debug") &&
                    it.name.contains("DependenciesMetadata") && (
                    it.name.startsWithAny("api", "implementation", "runtimeOnly") ||
                            it.name.contains("Api") ||
                            it.name.contains("Implementation") ||
                            it.name.contains("RuntimeOnly")
                    )
        }.map { it.name }
    }
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        project(":data"),
        project(":domain"),
        project(":base"),
        project(":baseAndroid"),
        project(":ui:compose"),
        project(":features:shared"),
        project(":features:homepage"),
        project(":features:payments"),
        project(":features:chart"),
        Libraries.Androidx.core,
        Libraries.Androidx.Compose.activityCompose
    )

    useDagger()
    useCompose(true, true)
}
