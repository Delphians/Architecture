import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useCompose
import com.che.architecture.utils.useDagger
import com.che.architecture.utils.useJUnitPlatform

plugins {
    id("architecture-plugin")
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.che.architecture.features.shared"
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        project(":ui:compose"),
        project(":base")
    )
    useCompose(isNavigation = true)
    useDagger(true)

    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        Libraries.Coroutines.test
    )

    useJUnitPlatform()
}
