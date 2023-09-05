package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.FeaturedPlaylists
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

const val refreshIntervalMs = 6000000L

class FeaturedPlaylistsRepository() {
    // Data source
    private val _featuredPlaylists: Flow<FeaturedPlaylists> = flow {
        while (true) {
            guardValidSpotifyApi { api ->
                emit(api.browse.getFeaturedPlaylists())
            }
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
    val featuredPlaylists = _featuredPlaylists
}

