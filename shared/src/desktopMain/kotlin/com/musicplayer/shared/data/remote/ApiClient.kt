package com.musicplayer.shared.data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual class ApiClient {
    actual val httpClient: HttpClient = createHttpClient(OkHttp.create())
}

