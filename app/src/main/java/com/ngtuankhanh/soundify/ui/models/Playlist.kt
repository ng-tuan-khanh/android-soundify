package com.ngtuankhanh.soundify.ui.models

class Playlist {
    var Name: String? = null
    var ID: String? = null
    var owner_ID: String? = null
    var album_ID: String? = null
    lateinit var BackgroundImage: Image
    lateinit var TotalFollower: Followers
    var ListOfTracks: Array<Track> = arrayOf()
}

class PlaylistIcon {
    var Name: String? = null
    var ID: String? = null
    lateinit var BackgroundImage: Image
}