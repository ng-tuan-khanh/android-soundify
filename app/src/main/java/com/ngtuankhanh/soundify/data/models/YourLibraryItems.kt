package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Album
import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.ui.models.ItemType
import com.ngtuankhanh.soundify.ui.models.Item

data class YourLibraryItems(
    val tracks: List<Track>,
    val albums: List<Album>,
    val playlists: List<SimplePlaylist>,
    val artists: List<Artist>
) {
    fun toUIModels(): List<Item> {
        val items = mutableListOf<Item>()
        items.addAll(tracks.map {
            Item(
                id = it.id,
                name = it.name,
                imageUrl = it.album.images[0].url,
                type = ItemType.Track
            )
        })
        items.addAll(albums.map {
            Item(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = ItemType.Album
            )
        })
        items.addAll(playlists.map {
            Item(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = ItemType.Playlist
            )
        })
        items.addAll(artists.map {
            Item(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = ItemType.Artist
            )
        })
        return items
    }
}
