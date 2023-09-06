package com.ngtuankhanh.soundify.data.models

import com.ngtuankhanh.soundify.ui.models.Track

class Album {
    var id: String? = null
    var name: String? = null
    var artistId: String? = null
    var artistName: String? = null
    var imageUrl: String? = null
    var releaseUpdate: String? = null
    var listOfTrack: List<Track> = listOf()
}