package com.ngtuankhanh.soundify.ui.models

class Track {
    var name: String? = null
    var id: String? = null
    //lateinit var backgroundImage: Image

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Track

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}