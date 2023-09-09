package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Album
import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.SimplePlaylist
import com.adamratzman.spotify.models.Track

data class YourLibraryItems(
    val tracks: List<Track>,
    val albums: List<Album>,
    val playlists: List<SimplePlaylist>,
    val artists: List<Artist>
)