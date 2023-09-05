package com.ngtuankhanh.soundify.ui.models

class Album {
    var Name: String? = null
    var ID: String? = null
    var artist_ID: String? = null
    var album_ID: String? = null
    lateinit var BackgroundImage: Image
    lateinit var TotalFollower: Followers
    var ListOfTracks: Array<Track> = arrayOf()
}