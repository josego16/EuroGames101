package com.eurogames

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect fun getBaseUrl(): String