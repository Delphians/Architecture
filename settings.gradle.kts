rootProject.name = "Architecture"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":app")
include(":base")
include(":baseAndroid")
include(":data")
include(":domain")
include(":features:homepage")
include(":features:payments")
include(":features:chart")
include(":features:shared")
include(":ui:compose")
include(":shared")
