package com.ktm.kshape.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.ktm.kshape.R
import com.ktm.kshape.network.NetworkUtil
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.LinkedList

object BitmapPool {

    private const val THRESHOLD = 20

    private val bitmapQueue = LinkedList<Bitmap>()

    fun getBitmap(context: Context): Bitmap? {
        val bitmap = if (bitmapQueue.isNotEmpty()) {
            bitmapQueue.pop()
        } else {
            BitmapFactory.decodeResource(context.resources, R.drawable.image_test)
        }
        if (bitmapQueue.size < THRESHOLD) {
            fetchBitmap(context)
        }
        return bitmap
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchBitmap(context: Context, requestCount: Int = 10) {
        repeat(requestCount) {
            GlobalScope.launch {
                runCatching {
                    NetworkUtil.colorService.getRandomImage()?.let { response ->
                        val imageUrl = response[0].imageUrl
                        val bitmap = Glide.with(context)
                            .asBitmap()
                            .load(imageUrl)
                            .submit(100, 100).get()
                        bitmapQueue.push(bitmap)
                    }
                }.onFailure {
                    log(it.toString())
                    GlideException("Glide error").logRootCauses("KShape")
                }
            }
        }
    }
}