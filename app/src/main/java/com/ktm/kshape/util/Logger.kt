package com.ktm.kshape.util

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.ktm.kshape.shape.base.Shape

object Logger {

    private const val TAG = "KShape"

    fun log(className: String, message: String) {
        android.util.Log.d(TAG, "$className | $message")
    }
}

fun Activity.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}

fun Fragment.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}

fun View.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}

fun Shape.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}

fun ColorPool.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}

fun BitmapPool.log(message: String) {
    Logger.log(this::class.java.simpleName, message)
}