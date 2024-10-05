package com.ktm.kshape.util

import android.graphics.Color
import com.ktm.kshape.network.NetworkUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.LinkedList

object ColorPool {

    private const val THRESHOLD = 20

    private val colorQueue = LinkedList<Int>()

    fun getColor(): Int {
        val color = if (colorQueue.isNotEmpty()) {
            colorQueue.pop()
        } else {
            getRandomColor()
        }
        if (colorQueue.size < THRESHOLD) {
            fetchColor()
        }
        return color
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchColor(requestCount: Int = 20) {
        repeat(requestCount) {
            GlobalScope.launch {
                runCatching {
                    NetworkUtil.colorService.getRandomColor()?.let { response ->
                        val rgb = response[0].rgb
                        val color = Color.argb(255, rgb.red, rgb.green, rgb.blue)
                        colorQueue.push(color)
                    }
                }
            }
        }
    }
}