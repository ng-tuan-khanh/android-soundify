package com.ngtuankhanh.soundify.data.models

class Playlist {
    var ID: String? = null
    var Name: String? = null
    var Description: String? = null
    var Public: Boolean? = null
    var ImageURL: String? = null
    var TotalFollowers: Int? = null
    var ListOfTracks: Array<Track> = arrayOf()
}