package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimpleAlbum
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track

data class SearchResults (
    val albums: List<SimpleAlbum> = emptyList(),
    val artists: List<Artist> = emptyList(),
    val playlists: List<SimplePlaylist> = emptyList(),
    val tracks: List<Track> = emptyList()
)