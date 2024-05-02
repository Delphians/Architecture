import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useDagger

plugins {
    id("architecture-plugin")
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.che.architecture.base.android"
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        Libraries.Androidx.Compose.activityCompose,
        Libraries.Androidx.lifecycleProcess
    )

    useDagger()
}
