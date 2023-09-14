package com.ngtuankhanh.soundify.ui.models

import com.ngtuankhanh.soundify.ui.models.Image

class SearchItem {
    var name: String? = null
    var id: String? = null
    var avatarImage: Image? = null
    var type: ItemType = ItemType.TRACK

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchItem

        if (name != other.name) return false
        if (id != other.id) return false
        if (avatarImage != other.avatarImage) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (avatarImage?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}


enum class ItemType {
    PLAYLIST, ARTIST, TRACK
}
