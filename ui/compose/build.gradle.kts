import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useCompose

plugins {
    id("architecture-plugin")
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.che.architecture.ui.compose"
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        Libraries.Androidx.core
    )
    useCompose(isFullPackage = true)
}
