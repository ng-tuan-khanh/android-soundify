package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.utils.Market
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.data.models.Album
import com.ngtuankhanh.soundify.data.models.Playlist
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CollectionInfoRepository(private val activity: BaseActivity?) {
    fun getPlaylist(playlistId: String) = flow {
        var res: Playlist? = null
        activity?.guardValidSpotifyApi { api ->
            api.playlists.getPlaylist(playlist = playlistId, market = Market.VN)
        }?.let {
            res = Playlist(
                id = it.id,
                name = it.name,
                images = it.images,
                description = it.description,
                tracks = it.tracks.items.map { it.track }
            )
        }
        emit(res)
    }.flowOn(Dispatchers.IO)

    fun getAlbum(albumId: String) = flow {
        var res: Album? = null
        activity?.guardValidSpotifyApi { api ->
            api.albums.getAlbum(albumId)
        }?.let {
            res = Album(
                id = it.id,
                name = it.name,
                images = it.images,
                artists = it.artists.map { it.name },
                tracks = it.tracks.items.map { simpleTrack -> simpleTrack.toFullTrack(market = Market.VN) }
            )
        }
        emit(res)
    }.flowOn(Dispatchers.IO)
}