import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
import com.che.architecture.utils.useCompose
import com.che.architecture.utils.useDagger
import com.che.architecture.utils.useJUnitPlatform

plugins {
    id("architecture-plugin")
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    add(
        configurationName = ConfigurationName.IMPLEMENTATION,
        Libraries.Coroutines.core
    )

    useDagger(false)
    useCompose()

    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        Libraries.Coroutines.test
    )

    useJUnitPlatform()
}