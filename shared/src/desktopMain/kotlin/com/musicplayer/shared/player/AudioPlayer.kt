package com.musicplayer.shared.player

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Desktop implementation of AudioPlayer (stub for now)
 * TODO: Implement using JavaFX Media or vlcj for desktop audio playback
 */
actual class AudioPlayer {
    private val _isPlaying = MutableStateFlow(false)
    actual val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()
    
    private val _currentPosition = MutableStateFlow(0L)
    actual val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()
    
    private val _duration = MutableStateFlow(0L)
    actual val duration: StateFlow<Long> = _duration.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    actual val error: StateFlow<String?> = _error.asStateFlow()
    
    actual fun play(url: String) {
        _error.value = "Audio playback not yet implemented for Desktop"
        println("Desktop AudioPlayer: play($url) - not implemented")
    }
    
    actual fun pause() {
        println("Desktop AudioPlayer: pause() - not implemented")
    }
    
    actual fun resume() {
        println("Desktop AudioPlayer: resume() - not implemented")
    }
    
    actual fun stop() {
        println("Desktop AudioPlayer: stop() - not implemented")
    }
    
    actual fun seekTo(position: Long) {
        println("Desktop AudioPlayer: seekTo($position) - not implemented")
    }
    
    actual fun release() {
        println("Desktop AudioPlayer: release() - not implemented")
    }
}

