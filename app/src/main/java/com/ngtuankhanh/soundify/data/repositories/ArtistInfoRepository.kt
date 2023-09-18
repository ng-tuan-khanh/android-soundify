package com.ngtuankhanh.soundify.data.repositories

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimpleAlbum
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.auth.guardValidSpotifyApi
import com.ngtuankhanh.soundify.ui.activities.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArtistInfoRepository(private val activity: BaseActivity?) {
    fun getArtistInfo(artistId: String) = flow {
        activity?.guardValidSpotifyApi { api ->
            api.artists.getArtist(artistId)
        }?.let {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistTopTracks(artistId: String) = flow {
        activity?.guardValidSpotifyApi { api ->
            api.artists.getArtistTopTracks(artistId)
        }?.let {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    fun getArtistAlbums(artistId: String) = flow {
        activity?.guardValidSpotifyApi { api ->
            api.artists.getArtistAlbums(artistId)
        }?.let {
            emit(it.items)
        }
    }.flowOn(Dispatchers.IO)
}