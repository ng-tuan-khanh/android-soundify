package com.ngtuankhanh.soundify.data.models

import com.google.gson.annotations.SerializedName

data class FollowerData(
    @SerializedName("total")
    var Total: Int
)

data class ImageData(
    @SerializedName("url")
    var URL: String,

    @SerializedName("height")
    var Height: Int,

    @SerializedName("weight")
    var Weight: Int
)
