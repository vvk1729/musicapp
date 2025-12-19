package com.musicplayer.shared.player

import kotlinx.coroutines.flow.StateFlow

/**
 * Common audio player interface for multiplatform support
 */
expect class AudioPlayer() {
    val isPlaying: StateFlow<Boolean>
    val currentPosition: StateFlow<Long>
    val duration: StateFlow<Long>
    val error: StateFlow<String?>

    fun play(url: String)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position: Long)
    fun release()
}

