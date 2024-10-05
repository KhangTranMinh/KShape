package com.ktm.kshape.network

import com.google.gson.annotations.SerializedName

class RandomImageResponse : ArrayList<RandomImageResponse.ImageItem>() {

    class ImageItem(
        @SerializedName("imageUrl") val imageUrl: String
    )
}