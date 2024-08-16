package com.che.architecture

object Versions {
    const val appVersion = "1.0.0"

    const val desugaring = "1.1.5"
    const val detekt = "1.23.5"
    const val junitPlatform = "5.10.2"
    const val ktor = "2.3.9"
    const val mockk = "1.13.10"

    object Androidx {
        const val core = "1.12.0"
        const val activityCompose = "1.8.2"
        const val lifecycleVersion = "2.7.0"

        object Compose {
            private const val core = "1.6.6"
            const val compiler = "1.5.11"
            const val runtime = core
            const val ui = core
            const val foundation = core
            const val tooling = core
            const val material3 = "1.2.0"
            const val navigation = "2.7.7"
        }
    }

    object Kotlinx {
        const val coroutines = "1.8.0"
        const val serialization = "1.6.3"
        const val collection = "0.3.7"
        const val dateTime = "0.6.0"
    }
}

object Libraries {
    const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"

    object Androidx {
        const val core = "androidx.core:core-ktx:${Versions.Androidx.core}"

        object Compose {
            const val activityCompose =
                "androidx.activity:activity-compose:${Versions.Androidx.activityCompose}"
            const val runtime =
                "androidx.compose.runtime:runtime:${Versions.Androidx.Compose.runtime}"
            const val ui = "androidx.compose.ui:ui:${Versions.Androidx.Compose.ui}"
            const val foundation =
                "androidx.compose.foundation:foundation:${Versions.Androidx.Compose.foundation}"
            const val uiTooling =
                "androidx.compose.ui:ui-tooling:${Versions.Androidx.Compose.tooling}"
            const val material =
                "androidx.compose.material3:material3:${Versions.Androidx.Compose.material3}"
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.Androidx.Compose.navigation}"
        }

        const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:" +
                Versions.Androidx.lifecycleVersion
    }

    object Kotlinx {
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlinx.serialization}"

        const val immutableCollections =
            "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.Kotlinx.collection}"

        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlinx.dateTime}"
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlinx.coroutines}"
        const val test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlinx.coroutines}"
    }

    object KtorClient {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val cio = "io.ktor:ktor-client-cio:${Versions.ktor}"
        const val content_negotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val ktorMockClient = "io.ktor:ktor-client-mock:${Versions.ktor}"
    }

    object Mockk {
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    }
}




