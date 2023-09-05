package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.CursorBasedPagingObject
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.SavedAlbum
import com.adamratzman.spotify.models.SavedTrack
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.YourLibraryItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class YourLibraryItemsRepository() {
    // Data sources
    private val _tracks: Flow<PagingObject<SavedTrack>> = flow {
        while (true) {
            guardValidSpotifyApi { api ->
                emit(api.library.getSavedTracks())
            }
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
    private val _albums: Flow<PagingObject<SavedAlbum>> = flow {
        while (true) {
            guardValidSpotifyApi { api ->
                emit(api.library.getSavedAlbums())
            }
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
    private val _artists: Flow<CursorBasedPagingObject<Artist>> = flow {
        while (true) {
            guardValidSpotifyApi { api ->
                emit(api.following.getFollowedArtists())
            }
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
    val yourLibraryItems = combine(_tracks, _albums, _artists) { tracks, albums, artists ->
        YourLibraryItems(tracks, albums, artists)
    }
}
