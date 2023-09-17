package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Album
import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track
import com.ngtuankhanh.soundify.ui.models.LibraryItemType
import com.ngtuankhanh.soundify.ui.models.YourLibraryItem

data class YourLibraryItems(
    val tracks: List<Track>,
    val albums: List<Album>,
    val playlists: List<SimplePlaylist>,
    val artists: List<Artist>
) {
    fun toUIModels(): List<YourLibraryItem> {
        val items = mutableListOf<YourLibraryItem>()
        items.addAll(tracks.map {
            YourLibraryItem(
                id = it.id,
                name = it.name,
                imageUrl = it.album.images[0].url,
                type = LibraryItemType.Track
            )
        })
        items.addAll(albums.map {
            YourLibraryItem(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = LibraryItemType.Album
            )
        })
        items.addAll(playlists.map {
            YourLibraryItem(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = LibraryItemType.Playlist
            )
        })
        items.addAll(artists.map {
            YourLibraryItem(
                id = it.id,
                name = it.name,
                imageUrl = it.images[0].url,
                type = LibraryItemType.Artist
            )
        })
        return items.shuffled()
    }
}
