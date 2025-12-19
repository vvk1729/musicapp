package com.musicplayer.shared.domain.usecase

import com.musicplayer.shared.domain.model.Track
import com.musicplayer.shared.domain.repository.MusicRepository

/**
 * Sort modes for tracks
 */
enum class SortMode {
    NAME,
    ARTIST,
    DURATION
}

/**
 * Use case for getting and sorting tracks
 */
class GetTracksUseCase(private val repository: MusicRepository) {

    suspend fun execute(sortMode: SortMode = SortMode.NAME): Result<List<Track>> {
        return try {
            val result = repository.getTracks()
            result.map { tracks ->
                when (sortMode) {
                    SortMode.NAME -> repository.getSortedByName(tracks)
                    SortMode.ARTIST -> repository.getSortedByArtist(tracks)
                    SortMode.DURATION -> repository.getSortedByDuration(tracks)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

