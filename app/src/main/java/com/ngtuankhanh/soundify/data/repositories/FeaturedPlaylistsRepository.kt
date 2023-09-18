package com.ngtuankhanh.soundify.data.repositories

import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.Playlist
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FeaturedPlaylistsRepository(private val activity: BaseActivity?) {
    // Data source
    fun getFeaturedPlaylists() = flow {
        val res = mutableListOf<Playlist>()
        activity?.guardValidSpotifyApi { api ->
            api.browse.getFeaturedPlaylists()
        }?.let {
            it.playlists.items.map { simplePlaylist ->
                simplePlaylist.toFullPlaylist()
            }.forEach { playlist ->
                playlist?.let {
                    res.add(
                        Playlist(
                            id = it.id,
                            name = it.name,
                            images = it.images,
                            description = it.description,
                            tracks = it.tracks.items.map { it.track })
                    )
                }
            }
        }
        emit(res)
    }.flowOn(Dispatchers.IO)
}

