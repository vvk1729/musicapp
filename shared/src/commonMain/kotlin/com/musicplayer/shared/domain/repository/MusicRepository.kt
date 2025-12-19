package com.musicplayer.shared.domain.repository

import com.musicplayer.shared.data.remote.JamendoApi
import com.musicplayer.shared.domain.model.Track

/**
 * Repository for music data
 */
class MusicRepository(private val api: JamendoApi) {

    suspend fun getTracks(): Result<List<Track>> {
        return api.fetchTracks()
    }

    fun getSortedByName(tracks: List<Track>): List<Track> {
        return tracks.sortedBy { it.name.lowercase() }
    }

    fun getSortedByDuration(tracks: List<Track>): List<Track> {
        return tracks.sortedBy { it.duration }
    }

    fun getSortedByArtist(tracks: List<Track>): List<Track> {
        return tracks.sortedBy { it.artistName.lowercase() }
    }
}

