package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.CursorBasedPagingObject
import com.adamratzman.spotify.models.PagingObject
import com.adamratzman.spotify.models.SavedAlbum
import com.adamratzman.spotify.models.SavedTrack
data class YourLibraryItems(
    val tracks: PagingObject<SavedTrack>,
    val albums: PagingObject<SavedAlbum>,
    val artists: CursorBasedPagingObject<Artist>
) {
    val items: List<Any> = tracks.items + albums.items + artists.items
}