package com.ngtuankhanh.soundify.ui.models

class Artist {
    var Name: String? = null
    var ID: String? = null
    lateinit var AvatarImage: Image
    lateinit var BackgroundImage: Image
    lateinit var TotalFollower: Followers
    var ListOfTracks: Array<Track> = arrayOf()
    var ListOfAlbums: Array<Album> = arrayOf()
}