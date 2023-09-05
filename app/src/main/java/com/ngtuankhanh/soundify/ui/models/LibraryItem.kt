package com.ngtuankhanh.soundify.ui.models

enum class LibraryItemType {
    PLAYLIST, ALBUM, ARTIST
}

class LibraryItem {
    var ID: String? = null
    var Name: String? = null
    var AvatarImage: Image? = null
    lateinit var Type: LibraryItemType
}