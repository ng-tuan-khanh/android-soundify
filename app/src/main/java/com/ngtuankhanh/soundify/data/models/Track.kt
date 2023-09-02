package com.ngtuankhanh.soundify.data.models

import com.google.gson.annotations.SerializedName

class Track {
    @SerializedName("id")
    var ID: String? = null

    @SerializedName("name")
    var Name: String? = null

    var Album_ID: String? = null
    var Artist_ID: String? = null
    var URL: String? = null
}