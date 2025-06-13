package com.eurogames.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun httpClientEngine(): HttpClientEngine = OkHttp.create {
    // You can configure OkHttp client here if needed
    // For example, you can set timeouts, interceptors, etc.
    // Example: config { connectTimeout(30, TimeUnit.SECONDS) }
}