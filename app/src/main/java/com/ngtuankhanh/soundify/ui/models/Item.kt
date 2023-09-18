package com.ngtuankhanh.soundify.ui.models

data class Item (
    val id: String,
    val name: String,
    val artists: List<String> = emptyList(),
    val imageUrl: String,
    val type: ItemType,
    val previewUrl: String = ""
) {
}

enum class ItemType {
    Track, Album, Playlist, Artist
}