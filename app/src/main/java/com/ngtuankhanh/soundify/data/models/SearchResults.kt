package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimpleAlbum
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.ui.models.Image
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.models.SearchItem

data class SearchResults (
    val albums: List<SimpleAlbum> = emptyList(),
    val artists: List<Artist> = emptyList(),
    val playlists: List<SimplePlaylist> = emptyList(),
    val tracks: List<Track> = emptyList()
) {
    fun toUIModels() : List<SearchItem> {
        val list = (albums.map {
            SearchItem(
                name = it.name,
                id = it.id,
                avatarImage = Image(it.images.firstOrNull()?.url ?: ""),
                type = ItemType.Album
            )
        } + artists.map {
            SearchItem(
                name = it.name,
                id = it.id,
                avatarImage = Image(it.images.firstOrNull()?.url ?: ""),
                type = ItemType.Artist
            )
        } + playlists.map {
            SearchItem(
                name = it.name,
                id = it.id,
                avatarImage = Image(it.images.firstOrNull()?.url ?: ""),
                type = ItemType.Playlist
            )
        } + tracks.map {
            SearchItem(
                name = it.name,
                id = it.id,
                avatarImage = Image(it.album.images.firstOrNull()?.url ?: ""),
                type = ItemType.Track
            )
        }).shuffled()

        return list
    }
}