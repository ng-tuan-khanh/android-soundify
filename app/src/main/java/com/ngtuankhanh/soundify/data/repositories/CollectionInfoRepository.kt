package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.Album
import com.adamratzman.spotify.models.Playlist
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CollectionInfoRepository {
    private val _playlists: MutableMap<String, Playlist> = mutableMapOf()
    private val _albums: MutableMap<String, Album> = mutableMapOf()
    fun getPlaylist(playlistId: String) = flow {
        if (_playlists.containsKey(playlistId)) {
            emit(_playlists[playlistId]!!)
            return@flow
        }
        var res: Playlist? = null
        guardValidSpotifyApi { api ->
            res = api.playlists.getPlaylist(playlistId)
        }
        emit(res)
        res?.let{
            _playlists[playlistId] = it
        }
    }.flowOn(Dispatchers.IO)

    fun getAlbum(albumId: String) = flow {
        if (_albums.containsKey(albumId)) {
            emit(_albums[albumId]!!)
            return@flow
        }
        var res: Album? = null
        guardValidSpotifyApi { api ->
            res = api.albums.getAlbum(albumId)
        }
        emit(res)
        res?.let{
            _albums[albumId] = it
        }
    }.flowOn(Dispatchers.IO)
}