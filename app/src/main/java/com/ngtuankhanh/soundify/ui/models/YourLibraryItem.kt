package com.ngtuankhanh.soundify.ui.models

enum class LibraryItemType {
    Track, Album, Playlist, Artist
}

data class YourLibraryItem (
    val id: String,
    val name: String,
    val imageUrl: String,
    val type: LibraryItemType
)