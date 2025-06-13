package com.eurogames.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun httpClientEngine(): HttpClientEngine = Darwin.create {
    // Configure the Darwin HTTP client engine if needed
    // For example, you can set timeouts, logging, etc.
    // This is optional and can be customized based on your requirements.
}