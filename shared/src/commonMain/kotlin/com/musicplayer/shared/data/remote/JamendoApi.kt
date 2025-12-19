package com.musicplayer.shared.data.remote

import com.musicplayer.shared.domain.model.Track
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Jamendo API client for fetching music tracks
 */
class JamendoApi(private val apiClient: ApiClient) {
    private val client = apiClient.client
    private val baseUrl = "https://api.jamendo.com/v3.0"

    suspend fun fetchTracks(limit: Int = 50): Result<List<Track>> {
        return try {
            println("JamendoApi: Fetching tracks from $baseUrl/tracks/")
            val response: JamendoApiResponse = client.get("$baseUrl/tracks/") {
                parameter("client_id", "56d30c95")
                parameter("format", "json")
                parameter("limit", limit)
                parameter("audioformat", "mp32") // Full MP3 audio
                parameter("imagesize", "500") // Large album art (500x500)
                parameter("include", "musicinfo")
            }.body()

            println("JamendoApi: Received ${response.results.size} tracks")
            val tracks = response.results.map { it.toTrack() }
            Result.success(tracks)
        } catch (e: Exception) {
            e.printStackTrace()
            println("JamendoApi: Error fetching tracks - ${e.message}")
            Result.failure(Exception("Failed to load tracks: ${e.message}"))
        }
    }
}

@Serializable
private data class JamendoApiResponse(
    val results: List<JamendoTrackResponse>
)

@Serializable
private data class JamendoTrackResponse(
    val id: String,
    val name: String,
    @SerialName("artist_name")
    val artistName: String,
    val duration: Int,
    val audio: String, // Full-length MP3 URL
    val image: String // Album art URL (500x500)
) {
    fun toTrack(): Track {
        return Track(
            id = id,
            name = name,
            artistName = artistName,
            duration = duration,
            audioUrl = audio,
            imageUrl = image
        )
    }
}

