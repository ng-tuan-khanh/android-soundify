package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimpleAlbum
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.models.Item

data class SearchResults (
    val albums: List<SimpleAlbum> = emptyList(),
    val artists: List<Artist> = emptyList(),
    val playlists: List<SimplePlaylist> = emptyList(),
    val tracks: List<Track> = emptyList()
) {
    fun toUIModels() : List<Item> {
        val list = (albums.map {
            Item(
                name = it.name,
                id = it.id,
                artists = it.artists.map { it.name },
                imageUrl = it.images.firstOrNull()?.url ?: "",
                type = ItemType.Album
            )
        } + artists.map {
            Item(
                name = it.name,
                id = it.id,
                imageUrl = it.images.firstOrNull()?.url ?: "",
                type = ItemType.Artist
            )
        } + playlists.map {
            Item(
                name = it.name,
                id = it.id,
                imageUrl = it.images.firstOrNull()?.url ?: "",
                type = ItemType.Playlist
            )
        } + tracks.map {
            Item(
                name = it.name,
                id = it.id,
                artists = it.artists.map { it.name },
                imageUrl = it.album.images.firstOrNull()?.url ?: "",
                previewUrl = it.previewUrl ?: "",
                type = ItemType.Track
            )
        }).shuffled()

        return list
    }
}