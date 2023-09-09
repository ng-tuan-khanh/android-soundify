package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.FeaturedPlaylists
import com.adamratzman.spotify.utils.Market
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.Playlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

const val refreshIntervalMs = 6000000L

class FeaturedPlaylistsRepository() {
    // Data source
    private val _featuredPlaylistsIds: Flow<List<String>> = flow {
        while (true) {
            lateinit var res: FeaturedPlaylists
            guardValidSpotifyApi { api ->
                res = api.browse.getFeaturedPlaylists()
            }
            emit(res)
            delay(refreshIntervalMs)
        }
    }.map { it ->
        it.playlists.items.map { it.id }
    }.flowOn(Dispatchers.IO)

    fun getFeaturedPlaylists() = flow {
        _featuredPlaylistsIds.collect { playlists ->
            val res = mutableListOf<Playlist>()
            playlists.forEach { id ->
                guardValidSpotifyApi { api ->
                    api.playlists.getPlaylist(playlist = id, market = Market.VN)
                }?.let {
                    res.add(
                        Playlist(
                            id = it.id,
                            images = it.images,
                            name = it.name,
                            owner = it.owner,
                            tracks = it.tracks.items.map {it.track}
                        )
                    )
                }
            }
            emit(res)
        }
    }.flowOn(Dispatchers.IO)
}

