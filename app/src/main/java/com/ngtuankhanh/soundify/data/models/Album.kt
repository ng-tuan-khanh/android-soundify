package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.SpotifyImage
import com.adamratzman.spotify.models.Track

data class Album(
    // The Spotify id used to retrieve the album
    val id: String,
    val name: String,
    val images: List<SpotifyImage>,
    val artists: List<String>,
    val tracks: List<Track?> = emptyList()
)