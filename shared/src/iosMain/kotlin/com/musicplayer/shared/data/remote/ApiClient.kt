package com.musicplayer.shared.data.remote

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual class ApiClient {
    actual val httpClient: HttpClient = createHttpClient(Darwin.create())
}

