package com.ngtuankhanh.soundify.ui.models

class Artist {
    var name: String? = null
    var id: String? = null
    //lateinit var avatarImage: Image
    //lateinit var backgroundImage: Image
    var listOfTracks: List<Track> = listOf()
    var listOfAlbums: List<PlaylistIcon> = listOf()
}