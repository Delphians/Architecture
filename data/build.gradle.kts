import com.che.architecture.Libraries
import com.che.architecture.utils.ConfigurationName
import com.che.architecture.utils.add
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
        project(":domain"),
        project(":base"),
        Libraries.KtorClient.core,
        Libraries.KtorClient.cio,
        Libraries.KtorClient.serialization,
        Libraries.KtorClient.content_negotiation
    )

    useDagger(isAndroidModule = false)

    add(
        configurationName = ConfigurationName.TEST_IMPLEMENTATION,
        Libraries.KtorClient.ktorMockClient,
        Libraries.Coroutines.test,
        "ch.qos.logback:logback-classic:1.2.9"
    )

    useJUnitPlatform()
}