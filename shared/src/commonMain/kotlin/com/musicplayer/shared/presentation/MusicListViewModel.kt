package com.musicplayer.shared.presentation

import com.musicplayer.shared.domain.model.Track
import com.musicplayer.shared.domain.repository.MusicRepository
import com.musicplayer.shared.domain.usecase.GetTracksUseCase
import com.musicplayer.shared.domain.usecase.SortMode
import com.musicplayer.shared.player.AudioPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Platform-agnostic ViewModel for music list
 * This can be used on Android, iOS, and Desktop
 */
class MusicListViewModel(
    private val getTracksUseCase: GetTracksUseCase,
    private val repository: MusicRepository,
    private val audioPlayer: AudioPlayer
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()

    private val _sortMode = MutableStateFlow(SortMode.NAME)
    val sortMode: StateFlow<SortMode> = _sortMode.asStateFlow()

    // Audio player state
    val isPlaying: StateFlow<Boolean> = audioPlayer.isPlaying
    val currentPosition: StateFlow<Long> = audioPlayer.currentPosition
    val duration: StateFlow<Long> = audioPlayer.duration
    val error: StateFlow<String?> = audioPlayer.error

    private var allTracks: List<Track> = emptyList()
    private var sortedTracks: List<Track> = emptyList()
    private var currentTrackIndex = -1

    init {
        loadTracks()
    }

    fun loadTracks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val result = getTracksUseCase.execute(_sortMode.value)

                result.onSuccess { tracks ->
                    if (tracks.isNotEmpty()) {
                        allTracks = tracks
                        sortedTracks = tracks
                        _uiState.value = UiState.Success(sortedTracks)
                    } else {
                        loadMockData()
                    }
                }.onFailure {
                    loadMockData()
                }
            } catch (e: Exception) {
                loadMockData()
            }
        }
    }

    private fun loadMockData() {
        allTracks = listOf(
            Track(
                id = "1",
                name = "Chill Lo-Fi Beat",
                artistName = "Lofi Girl",
                duration = 180,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                imageUrl = "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=500&h=500&fit=crop"
            ),
            Track(
                id = "2",
                name = "Acoustic Dreams",
                artistName = "Free Music Archive",
                duration = 200,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
                imageUrl = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=500&h=500&fit=crop"
            ),
            Track(
                id = "3",
                name = "Electronic Vibes",
                artistName = "SoundHelix",
                duration = 167,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                imageUrl = "https://images.unsplash.com/photo-1571330735066-03aaa9429d89?w=500&h=500&fit=crop"
            ),
            Track(
                id = "4",
                name = "Jazz Fusion",
                artistName = "Jazz Collective",
                duration = 245,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
                imageUrl = "https://images.unsplash.com/photo-1415201179716-6ab25e3b6bf9?w=500&h=500&fit=crop"
            ),
            Track(
                id = "5",
                name = "Indie Rock Anthem",
                artistName = "The Wanderers",
                duration = 195,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
                imageUrl = "https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?w=500&h=500&fit=crop"
            )
        )
        applySorting()
    }

    fun sortByName() {
        _sortMode.value = SortMode.NAME
        applySorting()
    }

    fun sortByDuration() {
        _sortMode.value = SortMode.DURATION
        applySorting()
    }

    fun sortByArtist() {
        _sortMode.value = SortMode.ARTIST
        applySorting()
    }

    private fun applySorting() {
        sortedTracks = when (_sortMode.value) {
            SortMode.NAME -> repository.getSortedByName(allTracks)
            SortMode.DURATION -> repository.getSortedByDuration(allTracks)
            SortMode.ARTIST -> allTracks.sortedBy { it.artistName.lowercase() }
        }
        _uiState.value = UiState.Success(sortedTracks)
    }

    fun playTrack(track: Track) {
        currentTrackIndex = sortedTracks.indexOf(track)
        _currentTrack.value = track
        audioPlayer.play(track.audioUrl)
    }

    fun togglePlayPause() {
        if (isPlaying.value) {
            audioPlayer.pause()
        } else {
            audioPlayer.resume()
        }
    }

    fun seekTo(position: Long) {
        audioPlayer.seekTo(position)
    }

    fun playNext() {
        if (sortedTracks.isEmpty()) return
        currentTrackIndex = (currentTrackIndex + 1) % sortedTracks.size
        val nextTrack = sortedTracks[currentTrackIndex]
        playTrack(nextTrack)
    }

    fun playPrevious() {
        if (sortedTracks.isEmpty()) return
        currentTrackIndex = if (currentTrackIndex - 1 < 0) {
            sortedTracks.size - 1
        } else {
            currentTrackIndex - 1
        }
        val previousTrack = sortedTracks[currentTrackIndex]
        playTrack(previousTrack)
    }

    fun onCleared() {
        audioPlayer.release()
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val tracks: List<Track>) : UiState()
    data class Error(val message: String) : UiState()
}

