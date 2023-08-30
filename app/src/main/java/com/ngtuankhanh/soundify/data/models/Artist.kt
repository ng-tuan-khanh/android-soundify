package com.ngtuankhanh.soundify.data.models

class Artist {
    var Name: String? = null
    var ID: String? = null
    var ImageURL: String? = null
    var TotalFollower: Int? = null
    var ListOfTracks: Array<Track> = arrayOf()
    var ListOfAlbums: Array<Album> = arrayOf()
}