package com.ngtuankhanh.soundify.data.models

import com.google.gson.annotations.SerializedName

class Artist {
    @SerializedName("id")
    var ID: String? = null

    @SerializedName("name")
    var Name: String? = null

    var ImageURL: String? = null
    var TotalFollower: Int? = null
    var ListOfTracks: Array<Track> = arrayOf()
    var ListOfAlbums: Array<Album> = arrayOf()
}