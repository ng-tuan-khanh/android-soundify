package com.ngtuankhanh.soundify.ui.models

data class TrackCollection(
    val id: String,
    val name: String,
    val artistName: String,
    val imageUrl: String,
    val totalTracks: Int,
    var isSoundOn: Boolean = false
)