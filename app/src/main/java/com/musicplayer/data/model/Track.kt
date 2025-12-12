package com.musicplayer.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    fun getFormattedDuration(): String {
        val minutes = duration / 60
        val seconds = duration % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}

@Serializable
data class JamendoResponse(
    val results: List<JamendoTrack>
)

@Serializable
data class JamendoTrack(
    val id: String,
    val name: String,
    @SerialName("artist_name")
    val artistName: String,
    val duration: String,
    @SerialName("audio")
    val audio: String,
    @SerialName("image")
    val image: String = ""
) {
    fun toTrack(): Track {
        return Track(
            id = id,
            name = name,
            artistName = artistName,
            duration = duration.toIntOrNull() ?: 0,
            audioUrl = audio,
            imageUrl = image
        )
    }
}
