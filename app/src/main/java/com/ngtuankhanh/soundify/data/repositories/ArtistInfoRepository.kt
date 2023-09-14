package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimpleAlbum
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtistInfoRepository {
    private val _artistInfo : MutableMap<String, Artist> = mutableMapOf()
    private val _artistTracks : MutableMap<String, List<Track>> = mutableMapOf()
    private val _artistAlbums : MutableMap<String, List<SimpleAlbum>> = mutableMapOf()
    fun getArtistInfo(artistId: String) = flow {
        if (_artistInfo.containsKey(artistId)) {
            emit(_artistInfo[artistId]!!)
            return@flow
        }
        guardValidSpotifyApi { api ->
            api.artists.getArtist(artistId)
        }?.let {
            emit(it)
            _artistInfo[artistId] = it
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistTopTracks(artistId: String) = flow {
        if (_artistTracks.containsKey(artistId)) {
            emit(_artistTracks[artistId]!!)
            return@flow
        }
        guardValidSpotifyApi { api ->
            api.artists.getArtistTopTracks(artistId)
        }?.let {
            emit(it)
            _artistTracks[artistId] = it
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistAlbums(artistId: String) = flow {
        if (_artistAlbums.containsKey(artistId)) {
            emit(_artistAlbums[artistId]!!)
            return@flow
        }
        guardValidSpotifyApi { api ->
            api.artists.getArtistAlbums(artistId)
        }?.let {
            emit(it.items)
            _artistAlbums[artistId] = it.items
        }
    }.flowOn(Dispatchers.IO)
}