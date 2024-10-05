package com.ktm.kshape.util

import android.graphics.Color

var screenWidth: Int = 0

fun getRandomColor(): Int {
    Logger.log("", "getRandomColor")
    return Color.argb(
        255,
        (0..255).random(),
        (0..255).random(),
        (0..255).random()
    )
}

fun getRandomShapeSize(): Int {
    val min = screenWidth * 0.1
    val max = screenWidth * 0.45
    return (min.toInt()..max.toInt()).random()
}