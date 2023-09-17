package com.ngtuankhanh.soundify.ui.models

data class TrackItem (
    val id: String,
    val name: String,
    val artists: List<String>,
    val imageUrl: String
)