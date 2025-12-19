package com.musicplayer.shared.di

import com.musicplayer.shared.data.remote.ApiClient
import com.musicplayer.shared.data.remote.JamendoApi
import com.musicplayer.shared.domain.repository.MusicRepository
import com.musicplayer.shared.domain.usecase.GetTracksUseCase
import com.musicplayer.shared.player.AudioPlayer
import com.musicplayer.shared.presentation.MusicListViewModel

/**
 * Dependency injection container for shared KMP module
 * This can be used from any platform (Android, iOS, Desktop)
 */
class SharedModule {

    private val apiClient by lazy { ApiClient() }
    private val jamendoApi by lazy { JamendoApi(apiClient) }
    private val musicRepository by lazy { MusicRepository(jamendoApi) }
    private val getTracksUseCase by lazy { GetTracksUseCase(musicRepository) }
    private val audioPlayer by lazy { AudioPlayer() }

    /**
     * Creates a new instance of MusicListViewModel
     * Call this from your platform-specific code
     */
    fun createViewModel(): MusicListViewModel {
        return MusicListViewModel(
            getTracksUseCase = getTracksUseCase,
            repository = musicRepository,
            audioPlayer = audioPlayer
        )
    }
}

