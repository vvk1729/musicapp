package com.musicplayer.shared.data.remote

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Common API client for all platforms
 * Uses expect/actual for platform-specific HTTP engines
 */
class ApiClient {
    val client = HttpClient(httpClientEngine()) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}

/**
 * Expect function for platform-specific HTTP engine
 * Actual implementations in androidMain, iosMain, desktopMain
 */
expect fun httpClientEngine(): io.ktor.client.engine.HttpClientEngine

