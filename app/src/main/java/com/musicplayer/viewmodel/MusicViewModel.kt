package com.musicplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.data.model.Track
import com.musicplayer.data.repository.MusicRepository
import com.musicplayer.player.AudioPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

sealed class UiState {
    object Loading : UiState()
    data class Success(val tracks: List<Track>) : UiState()
    data class Error(val message: String) : UiState()
}

enum class SortMode {
    NAME, DURATION
}

class MusicViewModel : ViewModel() {
    private val repository = MusicRepository()
    val audioPlayer = AudioPlayer()
    
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    private val _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack.asStateFlow()
    
    private val _sortMode = MutableStateFlow(SortMode.NAME)
    val sortMode: StateFlow<SortMode> = _sortMode.asStateFlow()
    
    private var allTracks: List<Track> = emptyList()
    private var sortedTracks: List<Track> = emptyList() // Currently displayed sorted list
    private var currentTrackIndex = -1
    
    init {
        loadTracks()
        startPositionUpdater()
    }
    
    fun loadTracks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            
            try {
                // Add timeout
                val result = withTimeout(10000) { // 10 second timeout
                    repository.getTracks()
                }
                
                result.onSuccess { tracks ->
                    if (tracks.isNotEmpty()) {
                        allTracks = tracks
                        applySorting()
                    } else {
                        // If API returns empty, use mock data
                        loadMockData()
                    }
                }.onFailure { error ->
                    // If API fails, use mock data instead of showing error
                    loadMockData()
                }
            } catch (e: Exception) {
                // Timeout or other error - use mock data
                loadMockData()
            }
        }
    }
    
    private fun loadMockData() {
        // Using publicly available full-length music with album art
        allTracks = listOf(
            Track(
                id = "1",
                name = "Chill Lo-Fi Beat",
                artistName = "Lofi Girl",
                duration = 180,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=500&h=500&fit=crop" // Music banner
            ),
            Track(
                id = "2",
                name = "Acoustic Dreams",
                artistName = "Free Music Archive",
                duration = 200,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=500&h=500&fit=crop" // Music banner
            ),
            Track(
                id = "3",
                name = "Electronic Vibes",
                artistName = "SoundHelix",
                duration = 167,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=500&h=500&fit=crop" // Music banner
            ),
            Track(
                id = "4",
                name = "Jazz Fusion",
                artistName = "Free Jazz Collective",
                duration = 220,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1415201364774-f6f0bb35f28f?w=500&h=500&fit=crop" // Music banner
            ),
            Track(
                id = "5",
                name = "Ambient Soundscape",
                artistName = "Ambient Collective",
                duration = 195,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1514320291840-2e0a9bf2a9ae?w=500&h=500&fit=crop" // Music banner
            ),
            Track(
                id = "6",
                name = "Rock Anthem",
                artistName = "Indie Rock Band",
                duration = 210,
                audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3", // Full song
                imageUrl = "https://images.unsplash.com/photo-1498038432885-c6f3f1b912ee?w=500&h=500&fit=crop" // Music banner
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
    
    private fun applySorting() {
        sortedTracks = when (_sortMode.value) {
            SortMode.NAME -> allTracks.sortedBy { it.name.lowercase() }
            SortMode.DURATION -> allTracks.sortedBy { it.duration }
        }
        _uiState.value = UiState.Success(sortedTracks)
    }
    
    fun playTrack(track: Track) {
        // Find index in the SORTED list (what user sees)
        currentTrackIndex = sortedTracks.indexOf(track)
        _currentTrack.value = track
        audioPlayer.play(track.audioUrl) {
            // Auto-play next track on completion
            playNext()
        }
    }
    
    fun togglePlayPause() {
        audioPlayer.togglePlayPause()
    }
    
    fun playNext() {
        if (sortedTracks.isEmpty()) return
        
        // Move to next track in the SORTED list
        currentTrackIndex = (currentTrackIndex + 1) % sortedTracks.size
        val nextTrack = sortedTracks[currentTrackIndex]
        playTrack(nextTrack)
    }
    
    fun playPrevious() {
        if (sortedTracks.isEmpty()) return
        
        // Move to previous track in the SORTED list
        currentTrackIndex = if (currentTrackIndex - 1 < 0) {
            sortedTracks.size - 1
        } else {
            currentTrackIndex - 1
        }
        val previousTrack = sortedTracks[currentTrackIndex]
        playTrack(previousTrack)
    }
    
    fun seekTo(position: Int) {
        audioPlayer.seekTo(position)
    }
    
    private fun startPositionUpdater() {
        viewModelScope.launch {
            while (isActive) {
                audioPlayer.updatePosition()
                delay(500) // Update every 500ms
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }
}
