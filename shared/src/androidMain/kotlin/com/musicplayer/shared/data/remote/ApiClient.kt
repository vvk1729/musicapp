package com.musicplayer.shared.data.remote

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

/**
 * Android implementation of HTTP client engine
 */
actual fun httpClientEngine(): HttpClientEngine = Android.create()

