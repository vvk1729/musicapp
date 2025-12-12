package com.musicplayer.data.repository

import com.musicplayer.data.model.Track
import com.musicplayer.data.remote.JamendoApi

class MusicRepository {
    private val api = JamendoApi()
    
    suspend fun getTracks(): Result<List<Track>> {
        return api.fetchTracks()
    }
}
