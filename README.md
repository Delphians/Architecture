Hello, Friends!   

# Tech stack of the base project:   
    `Clean Architecture, Single Activity, MVI, Hilt/Dagger, Coroutines, 
     Jetpack Compose, Material3, Atomic design, Ktor, JUnit, Mockk`

# The structure of the project:

* `app`               - main module
* `base`              - MVI architecture classes
* `buildSrc`          - Gradle settings and custom plugins
* `data`              - Ktor framework, Tiingo data source for getting stocks prices 
* `domain`            - Business logic and fake data 
* `features:shared`   - App Mvi classes, the logic for all features 
* `features:chart`    - Draw the fake chart
* `features:homepage` - The first screen displays the ticker (the circle is clickable). 
                        The second screen displays details about the ticker.
* `features:payments` - Show the fake close prices of the ticker for one year by days
* `ui`                - Compose components and Atomic foundation 

# What the app allows you to do? 

* The project supports `Light` and `Dark` mode, `rotation of a screen`
* Choosing different build variants - `production/staging (different tokens), debug/release`
* Start the static code analysis using Detekt - `./gradlew detekt`  
* Start the tests for processors, usecases, repositories and datasources - 
        `./gradlew test -x :app:test -PisEngBuild=false --no-build-cache`
* Analyzing dependencies for vulnerabilities - `./gradlew dependencyCheckAnalyze`
* Sending the deeplink which opens the app and displays the second homepage screen -
        `./adb shell am start -d "architecture://home_screen_details/GOOG" -W -a android.intent.action.VIEW` 
* it's necessary to have Tiingo token (sign-up for a free account) to get the real data from Tiingo 
  (https://www.tiingo.com/) and put the token into `buildConfigField` in `BaseExtensionConfigs` file.
  Also, in `InitialIntentionProcessor` (`features:payments` module)  use `dailyTickerPrices` usecase 
  (this line is commented out).   

# Future plans

* Check the network communication
* Add navigation tests  
* Add ExoPlayer(DASH over HTTPs), Location, Pushes, Room, GraphQL, Websockets, BLE, NFC features 

# If you have any questions

    https://www.linkedin.com/in/igor-chebotarev 
  