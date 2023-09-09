package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.Album
import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.CursorBasedPagingObject
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.SavedAlbum
import com.adamratzman.spotify.models.SavedTrack
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.YourLibraryItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class YourLibraryItemsRepository() {
    // Data sources
    private val _savedTracks: Flow<List<Track>> = flow {
        while (true) {
            lateinit var res: PagingObject<SavedTrack>
            guardValidSpotifyApi { api ->
                res = api.library.getSavedTracks()
            }
            emit(res)
            delay(refreshIntervalMs)
        }
    }.map { list ->
        list.items.map {
            it.track
        }
    }.flowOn(Dispatchers.IO)
    private val _savedAlbums: Flow<List<Album>> = flow {
        while (true) {
            lateinit var res: PagingObject<SavedAlbum>
            guardValidSpotifyApi { api ->
                res = api.library.getSavedAlbums()
            }
            emit(res)
            delay(refreshIntervalMs)
        }
    }.map { list ->
        list.items.map {
            it.album
        }
    }.flowOn(Dispatchers.IO)

    private val _savedPlaylists: Flow<List<SimplePlaylist>> = flow {
        val userId = guardValidSpotifyApi { api ->
            api.users.getClientProfile().id
        }
        while (true) {
            lateinit var res: List<SimplePlaylist>
            guardValidSpotifyApi { api ->
                res = api.playlists.getUserPlaylists(userId!!).items
            }
            emit(res)
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)

    private val _followedArtists: Flow<List<Artist>> = flow {
        while (true) {
            lateinit var res: CursorBasedPagingObject<Artist>
            guardValidSpotifyApi { api ->
                res = api.following.getFollowedArtists()
            }
            emit(res)
            delay(refreshIntervalMs)
        }
    }.map { list ->
        list.items
    }.flowOn(Dispatchers.IO)

    val yourLibraryItems: Flow<YourLibraryItems> = combine(
        _savedTracks,
        _savedAlbums,
        _savedPlaylists,
        _followedArtists
    ) { savedTracks, savedAlbums, savedPlaylists, followedArtists ->
        YourLibraryItems(
            tracks = savedTracks,
            albums = savedAlbums,
            playlists = savedPlaylists,
            artists = followedArtists
        )
    }.flowOn(Dispatchers.IO)
}
