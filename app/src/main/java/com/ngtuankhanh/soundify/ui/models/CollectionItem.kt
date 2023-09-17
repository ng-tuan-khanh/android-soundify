package com.ngtuankhanh.soundify.ui.models

data class CollectionItem(
    val id: String,
    val name: String,
    val artistName: String? = null,
    val imageUrl: String,
    val totalTracks: Int,
    val type: ItemType
)