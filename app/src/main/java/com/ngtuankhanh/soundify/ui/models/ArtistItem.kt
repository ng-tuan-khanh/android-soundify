package com.ngtuankhanh.soundify.ui.models

import com.ngtuankhanh.soundify.data.models.Playlist

class ArtistItem {
    var name: String? = null
    var id: String? = null
    //lateinit var avatarImage: Image
    //lateinit var backgroundImage: Image
    var listOfTrackItems: List<TrackItem> = listOf()
    var listOfAlbums: List<Playlist> = listOf()
}