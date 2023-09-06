package com.ngtuankhanh.soundify.ui.models

class Artist {
    var name: String? = null
    var id: String? = null
    lateinit var avatarImage: Image
    lateinit var backgroundImage: Image
    lateinit var totalFollowers: Followers
    var listOfTracks: List<Track> = listOf()
    var listOfAlbums: List<Album> = listOf()
}