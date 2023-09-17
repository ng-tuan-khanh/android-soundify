package com.ngtuankhanh.soundify.ui.models

class Playlist {
    var name: String? = null
    var id: String? = null
    var ownerId: String? = null
    var albumID: String? = null
    //lateinit var backgroundImage: Image
    //lateinit var totalFollower: Followers
    var listOfTracks: List<Track> = listOf()
}

class PlaylistIcon {
    var name: String? = null
    var id: String? = null
    //lateinit var backgroundImage: Image
}