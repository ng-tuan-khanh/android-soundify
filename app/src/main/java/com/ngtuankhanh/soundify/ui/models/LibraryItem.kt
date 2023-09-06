package com.ngtuankhanh.soundify.ui.models

enum class LibraryItemType {
    PLAYLIST, ALBUM, ARTIST
}

class LibraryItem {
    var id: String? = null
    var name: String? = null
    var avatarImage: Image? = null
    lateinit var type: LibraryItemType
}