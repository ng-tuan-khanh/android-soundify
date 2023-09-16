package com.ngtuankhanh.soundify.ui.models
data class SearchItem (
    val name: String,
    var id: String,
    val avatarImage: Image,
    val type: ItemType) {
}
enum class ItemType {
    Playlist, Album, Artist, Track
}
