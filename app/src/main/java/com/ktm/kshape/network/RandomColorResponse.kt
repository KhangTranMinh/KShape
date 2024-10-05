package com.ktm.kshape.network

import com.google.gson.annotations.SerializedName

class RandomColorResponse : ArrayList<RandomColorResponse.ColorItem>() {

    class ColorItem(
        @SerializedName("rgb") val rgb: Rgb
    ) {
        class Rgb(
            @SerializedName("red") val red: Int,
            @SerializedName("green") val green: Int,
            @SerializedName("blue") val blue: Int
        )
    }
}