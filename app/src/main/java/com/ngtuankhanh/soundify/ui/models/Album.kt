package com.ngtuankhanh.soundify.ui.models

class Album {
    var name: String? = null
    var id: String? = null
    var artistId: String? = null
    var albumId: String? = null
    lateinit var backgroundImage: Image
    lateinit var totalFollowers: Followers
    var listOfTracks: List<Track> = listOf()
}

data class DisplayAlbum(
    var id: String? = null,
    var name: String? = null,
    var artistName: String? = null,
    var imageUrl: String? = null,
    var totalTracks: Int? = null,
    var isSoundOn: Boolean = false
)