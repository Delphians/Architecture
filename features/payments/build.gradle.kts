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
    id("kotlin-kapt")
}

android {
    namespace = "com.che.architecture.features.payments"
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        project(":features:shared"),
        project(":ui:compose"),
        project(":base"),
        project(":baseAndroid"),
        project(":domain")
    )
    useDagger()
    useCompose(isFullPackage = true, isNavigation = true)

    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        Libraries.Coroutines.test,
        Libraries.Mockk.mockk,
        Libraries.Mockk.mockkAndroid
    )

    useJUnitPlatform()
}
