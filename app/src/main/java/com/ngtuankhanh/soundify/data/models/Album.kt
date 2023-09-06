package com.ngtuankhanh.soundify.data.models

class Album {
    var id: String? = null
    var name: String? = null
    var artistId: String? = null
    var artistName: String? = null
    var imageUrl: String? = null
    var releaseUpdate: String? = null
    var listOfTrack: List<Track> = listOf()
}

class DisplayAlbum {
    var ID: String? = null
    var Name: String? = null
    var ArtistName: String? = null
    var ImageURL: String? = null
    var TotalTracks: Int? = null
    var isSoundOn: Boolean = false
}