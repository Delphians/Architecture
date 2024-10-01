rootProject.name = "Architecture"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("plugins/common")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
include(":base")
include(":domain")
include(":data:common")
include(":data:remoteDatasource")
include(":data:localDatasource")
include(":atomicDesign")
include(":features:homepage")
include(":features:shared")
include(":features:chart")
include(":features:payments")
