package com.musicplayer.shared.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Music track data model
 */
@Serializable
data class Track(
    val id: String,
    val name: String,
    @SerialName("artist_name")
    val artistName: String,
    val duration: Int, // in seconds
    @SerialName("audio")
    val audioUrl: String,
    @SerialName("image")
    val imageUrl: String = ""
) {
    /**
     * Format duration as MM:SS
     */
    fun getFormattedDuration(): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}

