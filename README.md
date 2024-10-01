Hello, Friends!   

Kotlin Multiplatform application (Android + IOS).

The app downloads stock prices data from Tiingo. Show the list of the prices and draw a chart. 

# Tech stack of the base project:   
    `Clean Architecture, Single Activity, MVI, Coroutines, 
     Compose Multiplatform, Material3, Atomic design, Ktor, 
     Compose Navigation`

# The structure of the project:

* `app`                   - Shared module + Android Platform folder
* `atomicDesign`          - Compose components and Atomic foundation
* `base`                  - MVI base classes
* `data:common`           - Common repositories
* `data:remoteDatasource` - Ktor framework, Tiingo data source for getting stocks prices 
* `domain`                - Business logic and fake data 
* `features:shared`       - App Mvi classes, the logic for all features 
* `features:chart`        - Draw the fake chart
* `features:homepage`     - The first screen displays the ticker (the circle is clickable). 
                            The second screen displays details about the ticker.
* `features:payments`     - Show the fake close prices of the ticker for one year by days
* `iosApp`                - IOS Platform folder
* `plugins`               - Gradle settings and custom plugins

# What the app allows you to do? 

* The project supports `Light` and `Dark` mode
* Choosing different build variants - `production/staging, debug/release`
* Start the static code analysis using Detekt - `./gradlew detekt`  
* Tests for processors, usecases, repositories and datasources 

# Future plans

* Support rotation, lifecycle
* Check the network communication
* Add navigation tests  
* Add Room, ExoPlayer(DASH over HTTPs), Location (Google Maps SDK), Pushes, GraphQL, Websockets, 
BLE, NFC features 

# If you have any questions

    https://www.linkedin.com/in/igor-chebotarev 
  