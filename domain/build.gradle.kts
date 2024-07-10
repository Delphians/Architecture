import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useDagger
import com.che.architecture.utils.useJUnitPlatform

plugins {
    id("architecture-plugin")
    id("kotlin")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        project(":base"),
        Libraries.Kotlinx.serialization,
        Libraries.Coroutines.core,
        Libraries.Kotlinx.immutableCollections
    )

    useDagger(isAndroidModule = false)

    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        Libraries.Coroutines.test,
    )

    useJUnitPlatform()
}
