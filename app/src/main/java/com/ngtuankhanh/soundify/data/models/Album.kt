package com.ngtuankhanh.soundify.data.models

class Album {
    var ID: String? = null
    var Name: String? = null
    var ArtistID: String? = null
    var ArtistName: String? = null
    var ImageURL: String? = null
    var ReleaseDate: String? = null
    var ListOfTracks: Array<Track> = arrayOf()
}

class DisplayAlbum {
    var ID: String? = null
    var Name: String? = null
    var ArtistName: String? = null
    var ImageURL: String? = null
    var TotalTracks: Int? = null
    var isSoundOn: Boolean = false
}