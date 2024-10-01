package com.che.architecture

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
