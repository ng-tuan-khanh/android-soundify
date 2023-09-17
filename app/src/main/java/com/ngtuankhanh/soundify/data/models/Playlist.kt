package com.ngtuankhanh.soundify.data.models

import com.adamratzman.spotify.models.Playable
import com.adamratzman.spotify.models.SpotifyImage
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.Track

data class Playlist (
    // The Spotify id used to retrieve the playlist
    val id: String,
    val images: List<SpotifyImage>,
    val name: String,
    val description: String? = null,
    val tracks: List<Playable?> = emptyList()
)